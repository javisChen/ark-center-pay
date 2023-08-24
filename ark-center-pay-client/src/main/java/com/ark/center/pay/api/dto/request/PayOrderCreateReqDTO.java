package com.ark.center.pay.api.dto.request;

import java.io.Serializable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
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
public class PayOrderCreateReqDTO implements Serializable {

    @Schema(description = "订单id", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = ",不能为空")
    private Long orderId;

    @Schema(description = "支付类型编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "支付类型编码不能为空")
    private String payTypeCode;

    @Schema(description = "支付单描述信息", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;

}