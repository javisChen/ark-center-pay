package com.kt.cloud.pay.api.dto.mq;

import lombok.Data;

@Data
public class MQPayNotifyDTO {

    public String outTradeCode;

    public String tradeOrderCode;

    public String payOrderCode;

    public Integer result;
}
