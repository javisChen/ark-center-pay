package com.ark.center.pay.module.pay.event;

import com.alibaba.fastjson2.JSON;
import com.ark.center.pay.api.dto.mq.PayOrderCreatedMessage;
import com.ark.center.pay.module.pay.mq.MQConst;
import com.ark.component.mq.MsgBody;
import com.ark.component.mq.SendConfirm;
import com.ark.component.mq.SendResult;
import com.ark.component.mq.integration.MessageTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 角色API权限变更事件监听器
 * @author JC
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class PayEventListener {


    private final MessageTemplate messageTemplate;

    @EventListener
    public void onApplicationEvent(@NotNull PayOrderCreatedEvent event) {

        log.info("[支付单创建成功]：{}", event);

        PayOrderCreatedMessage message = new PayOrderCreatedMessage();
        message.setBizOrderId(event.getBizOrderId());
        message.setPayOrderId(event.getPayOrderId());
        message.setPayTradeNo(event.getPayTradeNo());

        messageTemplate.asyncSend(MQConst.TOPIC_PAY, MQConst.TAG_PAY_ORDER_CREATED, MsgBody.of(message), new SendConfirm() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("Message [pay created] Published successfully. body = [{}]", JSON.toJSONString(message));
            }

            @Override
            public void onException(SendResult sendResult) {
                log.info("Message [pay created] Published failure. msgId = {}", sendResult.getMsgId(), sendResult.getThrowable());
            }
        });
    }

}
