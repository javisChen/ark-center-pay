package com.ark.center.pay.module.paytype.dto.response;

import java.time.LocalDateTime;
import java.io.Serializable;
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
@Schema(description = "PayTypeRespDTO对象")
public class PayTypeRespDTO implements Serializable {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "支付类型名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "支付类型编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String code;

    @Schema(description = "支付单描述信息", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;

}
