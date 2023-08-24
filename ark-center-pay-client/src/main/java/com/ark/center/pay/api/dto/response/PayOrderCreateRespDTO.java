package com.ark.center.pay.api.dto.response;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;

/**
 * <p>
 * 支付订单信息
 * </p>
 *
 * @author EOP
 * @since 2022-08-11
 */
@Data
@Schema(description = "PayOrderCreateRespDTO")
public class PayOrderCreateRespDTO implements Serializable {

    @Schema(description = "支付单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long payOrderId;

    @Schema(description = "业务订单号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String bizTradeNo;

    @Schema(description = "支付类型编码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String payTypeCode;

}
