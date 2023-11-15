package com.ark.center.pay.module.pay.service;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ark.center.pay.acl.order.OrderServiceFacade;
import com.ark.center.pay.api.dto.mq.PayNotifyMessage;
import com.ark.center.pay.api.dto.request.PayOrderCreateCmd;
import com.ark.center.pay.api.dto.request.PayOrderPageQueryReqDTO;
import com.ark.center.pay.api.dto.response.PayOrderCreateDTO;
import com.ark.center.pay.dao.entity.PayOrderDO;
import com.ark.center.pay.dao.mapper.PayOrderMapper;
import com.ark.center.pay.module.pay.event.PayOrderCreatedEvent;
import com.ark.center.pay.module.pay.mq.MQConst;
import com.ark.center.trade.client.order.dto.OrderDTO;
import com.ark.component.dto.PageResponse;
import com.ark.component.exception.BizException;
import com.ark.component.exception.ExceptionFactory;
import com.ark.component.mq.MsgBody;
import com.ark.component.mq.SendConfirm;
import com.ark.component.mq.SendResult;
import com.ark.component.mq.integration.MessageTemplate;
import com.ark.component.orm.mybatis.base.BaseEntity;
import com.ark.component.web.util.bean.BeanConvertor;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

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

    private final MessageTemplate messageTemplate;
    private final OrderServiceFacade orderServiceFacade;
    private final ApplicationEventPublisher eventPublisher;

    public PayOrderCreateDTO createPayOrder(PayOrderCreateCmd createCmd) {
        log.info("[创建支付单]：入参：{}", createCmd);
        String payTypeCode = createCmd.getPayTypeCode();

        PayOrderDO payOrderDO = new PayOrderDO();

        Long bizOrderId = createCmd.getOrderId();
        OrderDTO order = orderServiceFacade.getOrder(bizOrderId);
        log.info("[创建支付单]：订单信息：{}", order);
        String bizTradeNo = order.getTradeNo();
        String payTradeNo = IdUtil.getSnowflakeNextIdStr();
        payOrderDO.setBizTradeNo(bizTradeNo);
        payOrderDO.setPayTradeNo(payTradeNo);
        payOrderDO.setPayTypeCode(payTypeCode);
        payOrderDO.setAmount(order.getActualAmount());
        payOrderDO.setDescription(createCmd.getDescription());
        payOrderDO.setStatus(PayOrderDO.Status.PENDING_PAY.getValue());
        log.info("[创建支付单]：支付单信息：{}", payOrderDO);
        save(payOrderDO);

        PayOrderCreateDTO dto = new PayOrderCreateDTO();
        Long payOrderId = payOrderDO.getId();
        dto.setPayOrderId(payOrderId);
        dto.setBizTradeNo(bizTradeNo);
        dto.setPayTypeCode(payTypeCode);

        // 发布事件
        eventPublisher.publishEvent(new PayOrderCreatedEvent(this, bizOrderId, payOrderId, payTradeNo, payTypeCode));

        return dto;
    }

    public PageResponse<PayOrderCreateDTO> getPageList(PayOrderPageQueryReqDTO queryDTO) {
        IPage<PayOrderCreateDTO> page = lambdaQuery()
                .orderByDesc(BaseEntity::getGmtCreate)
                .page(new Page<>(queryDTO.getCurrent(), queryDTO.getSize()))
                .convert(item -> BeanConvertor.copy(item, PayOrderCreateDTO.class));
        return BeanConvertor.copyPage(page, PayOrderCreateDTO.class);
    }

    public Map<String, Object> notify(JSONObject reqDTO) {
        log.info("[支付结果通知]：入参 = {}", reqDTO);
        String payTradeNo = reqDTO.getString("payTradeNo");
        Long orderId = reqDTO.getLong("orderId");
        Integer status = reqDTO.getInteger("status");
        PayOrderDO payOrder = getByTradeNo(payTradeNo);
        Assert.notNull(payOrder, () -> ExceptionFactory.userException("支付单不存在"));

        Long payOrderId = payOrder.getId();

        // 推送支付结果到MQ
        PayNotifyMessage dto = new PayNotifyMessage();
        dto.setOutTradeNo(payOrder.getOutTradeNo());
        dto.setBizTradeNo(payOrder.getBizTradeNo());
        dto.setPayOrderId(payOrderId);
        dto.setBizOrderId(orderId);
        dto.setPayTradeNo(payOrder.getPayTradeNo());
        dto.setResult(1);

        updatePayOrderStatus(payOrderId, status);
        messageTemplate.asyncSend(MQConst.TOPIC_PAY, MQConst.TAG_PAY_NOTIFY, MsgBody.of(dto), new SendConfirm() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("Publish pay result successfully. msg = {}, body = [{}]", sendResult.getMsgId(), JSON.toJSONString(dto));
            }

            @Override
            public void onException(SendResult sendResult) {
                log.error("Publish pay result failure. msgId = {}", sendResult.getMsgId(), sendResult.getThrowable());
            }
        });
        return Maps.newHashMap();
    }

    private PayOrderDO getByTradeNo(String payTradeNo) {
        return lambdaQuery()
                .eq(PayOrderDO::getPayTradeNo, payTradeNo)
                .last("limit 1")
                .one();
    }

    private void updatePayOrderStatus(Long payOrderId, Integer status) {
        lambdaUpdate()
                .eq(BaseEntity::getId, payOrderId)
                .set(PayOrderDO::getStatus, PayOrderDO.Status.PAY_SUCCESS.getValue())
                .update();
    }

    public PayOrderCreateDTO getPayOrderInfo(Long payOrderId) {
        PayOrderDO entity = getById(payOrderId);
        return BeanConvertor.copy(entity, PayOrderCreateDTO.class);
    }

    public Integer getPayOrderStatus(Long payOrderId) {
        PayOrderDO payOrderDO = lambdaQuery()
                .select(BaseEntity::getId, PayOrderDO::getStatus)
                .eq(BaseEntity::getId, payOrderId)
                .one();
        if (payOrderDO == null) {
            throw new BizException("支付单号不存在");
        }
        return payOrderDO.getStatus();
    }
}
