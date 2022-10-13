package com.ark.center.pay.api.dto.mq;

import lombok.Data;

@Data
public class MQPayNotifyDTO {

    public String outTradeNo;

    public String bizTradeNo;

    public Long orderId;

    public Long payOrderId;

    public String payTradeNo;

    public Integer result;
}
