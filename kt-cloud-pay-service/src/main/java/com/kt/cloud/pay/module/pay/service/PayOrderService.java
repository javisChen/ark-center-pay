package com.kt.cloud.pay.module.pay.service;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import com.kt.cloud.pay.api.dto.mq.MQPayNotifyDTO;
import com.kt.cloud.pay.dao.entity.PayOrderDO;
import com.kt.cloud.pay.dao.mapper.PayOrderMapper;
import com.kt.cloud.pay.api.dto.request.PayOrderCreateReqDTO;
import com.kt.cloud.pay.api.dto.request.PayOrderPageQueryReqDTO;
import com.kt.cloud.pay.api.dto.response.PayOrderCreateRespDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kt.cloud.pay.module.pay.mq.MQConst;
import com.kt.component.dto.PageResponse;
import com.kt.component.mq.Message;
import com.kt.component.mq.MessageResponse;
import com.kt.component.mq.MessageSendCallback;
import com.kt.component.mq.core.producer.MessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kt.component.web.util.bean.BeanConvertor;
import com.kt.component.orm.mybatis.base.BaseEntity;

import java.util.Map;

/**
 * <p>
 * 支付订单表 服务实现类
 * </p>
 *
 * @author EOP
 * @since 2022-08-11
 */
@Service
@Slf4j
public class PayOrderService extends ServiceImpl<PayOrderMapper, PayOrderDO> implements IService<PayOrderDO> {

    @Autowired
    private MessageProducer messageProducer;

    public PayOrderCreateRespDTO createPayOrder(PayOrderCreateReqDTO reqDTO) {
        PayOrderDO entity = new PayOrderDO();
        String bizOrderCode = reqDTO.getBizTradeNo();
        String payOrderCode = IdUtil.getSnowflakeNextIdStr();
        entity.setBizTradeNo(bizOrderCode);
        entity.setPayTradeNo(payOrderCode);
        entity.setPayTypeCode(reqDTO.getPayTypeCode());
        entity.setAmount(reqDTO.getAmount());
        entity.setDescription(reqDTO.getDescription());
        entity.setStatus(PayOrderDO.Status.PENDING_PAY.getValue());
        save(entity);
        PayOrderCreateRespDTO respDTO = new PayOrderCreateRespDTO();
        respDTO.setBizOrderCode(bizOrderCode);
        respDTO.setPayTypeCode(payOrderCode);
        return respDTO;
    }

    public PageResponse<PayOrderCreateRespDTO> getPageList(PayOrderPageQueryReqDTO queryDTO) {
        IPage<PayOrderCreateRespDTO> page = lambdaQuery()
                .orderByDesc(BaseEntity::getGmtCreate)
                .page(new Page<>(queryDTO.getCurrent(), queryDTO.getSize()))
                .convert(item -> BeanConvertor.copy(item, PayOrderCreateRespDTO.class));
        return BeanConvertor.copyPage(page, PayOrderCreateRespDTO.class);
    }

    public Map<String, Object> notify(JSONObject reqDTO) {
        // todo 逻辑后补
        // 推送支付结果到MQ
        MQPayNotifyDTO dto = new MQPayNotifyDTO();
        dto.setOutTradeCode(IdUtil.getSnowflakeNextIdStr());
        dto.setTradeOrderCode(IdUtil.getSnowflakeNextIdStr());
        dto.setPayOrderCode(IdUtil.getSnowflakeNextIdStr());
        dto.setResult(1);
        MessageSendCallback callback = new MessageSendCallback() {
            @Override
            public void onSuccess(MessageResponse messageResponse) {
//                log.info("send success -> {}", messageResponse);
            }

            @Override
            public void onException(Throwable throwable) {

            }
        };
        messageProducer.asyncSend(MQConst.TOPIC_PAY, MQConst.TAG_PAY_NOTIFY, new Message<>(dto), callback);
        return Maps.newHashMap();
    }

    public PayOrderCreateRespDTO getPayOrderInfo(Long payOrderId) {
        PayOrderDO entity = getById(payOrderId);
        return BeanConvertor.copy(entity, PayOrderCreateRespDTO.class);
    }

}
