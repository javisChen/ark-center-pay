package com.ark.center.pay.module.pay.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
@Getter
@Setter
public class PayOrderCreatedEvent extends ApplicationEvent {

    private Long bizOrderId;

    private Long payOrderId;

    private String payTradeNo;

    public PayOrderCreatedEvent(Object source) {
        super(source);
    }

    public PayOrderCreatedEvent(Object source, Long bizOrderId, Long payOrderId, String payTradeNo) {
        super(source);
        this.bizOrderId = bizOrderId;
        this.payOrderId = payOrderId;
        this.payTradeNo = payTradeNo;
    }

}
