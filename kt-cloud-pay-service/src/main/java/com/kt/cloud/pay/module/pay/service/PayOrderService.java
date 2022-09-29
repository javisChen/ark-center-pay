package com.kt.cloud.pay.module.pay.service;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import com.kt.cloud.order.api.response.OrderDetailRespDTO;
import com.kt.cloud.pay.acl.order.OrderServiceFacade;
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
import com.kt.component.exception.BizException;
import com.kt.component.mq.Message;
import com.kt.component.mq.MessageResponse;
import com.kt.component.mq.MessageSendCallback;
import com.kt.component.mq.core.producer.MessageProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequiredArgsConstructor
public class PayOrderService extends ServiceImpl<PayOrderMapper, PayOrderDO> implements IService<PayOrderDO> {

    private final MessageProducer messageProducer;
    private final OrderServiceFacade orderServiceFacade;
    public PayOrderCreateRespDTO createPayOrder(PayOrderCreateReqDTO reqDTO) {
        log.info("[创建支付单]：入参：{}", reqDTO);
        PayOrderDO payOrderDO = new PayOrderDO();
        OrderDetailRespDTO order = orderServiceFacade.getOrder(reqDTO.getOrderId());
        log.info("[创建支付单]：订单信息：{}", order);
        String bizTradeNo = order.getTradeNo();
        String payTradeNo = IdUtil.getSnowflakeNextIdStr();
        payOrderDO.setBizTradeNo(bizTradeNo);
        payOrderDO.setPayTradeNo(payTradeNo);
        payOrderDO.setPayTypeCode(reqDTO.getPayTypeCode());
        payOrderDO.setAmount(order.getActualAmount());
        payOrderDO.setDescription(reqDTO.getDescription());
        payOrderDO.setStatus(PayOrderDO.Status.PENDING_PAY.getValue());
        log.info("[创建支付单]：支付单信息：{}", payOrderDO);
        save(payOrderDO);
        PayOrderCreateRespDTO respDTO = new PayOrderCreateRespDTO();
        respDTO.setPayOrderId(payOrderDO.getId());
        respDTO.setBizTradeNo(bizTradeNo);
        respDTO.setPayTypeCode(reqDTO.getPayTypeCode());
        log.info("[创建支付单]：成功：{}", respDTO);
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
        log.info("[支付结果通知]：入参 = {}", reqDTO);
        Long payOrderId = reqDTO.getLong("payOrderId");
        Integer status = reqDTO.getInteger("status");
        PayOrderDO payOrder = getById(payOrderId);

        // 推送支付结果到MQ
        MQPayNotifyDTO dto = new MQPayNotifyDTO();
        dto.setOutTradeNo(payOrder.getOutTradeNo());
        dto.setBizTradeNo(payOrder.getBizTradeNo());
        dto.setPayOrderId(payOrder.getId());
        dto.setPayTradeNo(payOrder.getPayTradeNo());
        dto.setResult(1);

        updatePayOrderStatus(payOrderId, status);
        messageProducer.asyncSend(MQConst.TOPIC_PAY, MQConst.TAG_PAY_NOTIFY, new Message(dto), new MessageSendCallback() {
            @Override
            public void onSuccess(MessageResponse messageResponse) {
                log.info("send success -> {}", messageResponse);
            }

            @Override
            public void onException(Throwable throwable) {

            }
        });
        return Maps.newHashMap();
    }

    private void updatePayOrderStatus(Long payOrderId, Integer status) {
        lambdaUpdate()
                .eq(BaseEntity::getId, payOrderId)
                .set(PayOrderDO::getStatus, PayOrderDO.Status.PAY_SUCCESS.getValue())
                .update();
    }

    public PayOrderCreateRespDTO getPayOrderInfo(Long payOrderId) {
        PayOrderDO entity = getById(payOrderId);
        return BeanConvertor.copy(entity, PayOrderCreateRespDTO.class);
    }

    public Integer getPayOrderStatus(Long payOrderId) {
        PayOrderDO payOrderDO = lambdaQuery()
                .eq(BaseEntity::getId, payOrderId)
                .one();
        if (payOrderDO == null) {
            throw new BizException("支付单号不存在");
        }
        return payOrderDO.getStatus();
    }
}
