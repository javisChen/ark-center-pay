package com.ark.center.pay.api.dto.mq;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PayNotifyMessage {

    @Schema(name = "外部交易单号", description = "外部交易单号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String outTradeNo;

    @Schema(name = "业务交易单号", description = "业务交易单号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String bizTradeNo;

    @Schema(name = "业务订单id", description = "业务订单id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long bizOrderId;

    @Schema(name = "支付单id", description = "支付单id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long payOrderId;

    @Schema(name = "支付交易单号", description = "支付交易单号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String payTradeNo;

    @Schema(name = "支付结果", description = "1-成功 2-失败", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer result;
}
