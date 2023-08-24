package com.ark.center.pay.module.paytype.dto.request;

import java.time.LocalDateTime;
import java.io.Serializable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotBlank;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 支付类别表
 * </p>
 *
 * @author EOP
 * @since 2022-08-11
 */
@Data
@Schema(description = "PayTypeCreateReqDTO对象")
public class PayTypeCreateReqDTO implements Serializable {



    @Schema(description = "支付类型名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "支付类型名称不能为空")
    private String name;

    @Schema(description = "支付类型编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "支付类型编号不能为空")
    private String code;

    @Schema(description = "支付单描述信息", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "支付单描述信息不能为空")
    private String description;

}
