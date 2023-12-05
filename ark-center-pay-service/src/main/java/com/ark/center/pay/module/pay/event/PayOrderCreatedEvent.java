package com.ark.center.pay.module.pay.event;

import com.ark.center.pay.dao.entity.PayOrderDO;
import com.ark.component.common.ParamsChecker;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
@Getter
@Setter
public class PayOrderCreatedEvent extends ApplicationEvent {

    private PayOrderDO payOrder;

    public PayOrderCreatedEvent(Object source, PayOrderDO payOrder) {
        super(source);
        this.payOrder = payOrder;
    }

}
