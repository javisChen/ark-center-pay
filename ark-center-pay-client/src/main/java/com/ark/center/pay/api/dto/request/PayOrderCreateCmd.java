package com.ark.center.pay.api.dto.request;

import java.io.Serializable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 支付订单表
 * </p>
 *
 * @author EOP
 * @since 2022-08-11
 */
@Data
@Schema(description = "PayOrderCreateReqDTO")
public class PayOrderCreateCmd implements Serializable {

    @Schema(description = "业务交易单号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "业务交易单号不能为空")
    private String bizTradeNo;

    @Schema(description = "支付类型编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "支付类型编码不能为空")
    private String payTypeCode;

    @Schema(description = "支付类型id", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "支付类型id不能为空")
    private Integer payTypeId;

    @Schema(description = "支付单描述信息", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;

}