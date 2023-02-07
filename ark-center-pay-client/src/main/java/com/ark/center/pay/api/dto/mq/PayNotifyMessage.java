package com.ark.center.pay.api.dto.mq;

import lombok.Data;

@Data
public class PayNotifyMessage {

    private String outTradeNo;

    private String bizTradeNo;

    private Long orderId;

    private Long payOrderId;

    private String payTradeNo;

    private Integer result;
}
