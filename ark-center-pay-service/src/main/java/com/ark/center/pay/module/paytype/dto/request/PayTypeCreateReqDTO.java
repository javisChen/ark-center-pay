package com.ark.center.pay.module.paytype.dto.request;

import java.time.LocalDateTime;
import java.io.Serializable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotBlank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "PayTypeCreateReqDTO对象", description = "支付类别表")
public class PayTypeCreateReqDTO implements Serializable {



    @ApiModelProperty(value = "支付类型名称", required = true)
    @NotBlank(message = "支付类型名称不能为空")
    private String name;

    @ApiModelProperty(value = "支付类型编号", required = true)
    @NotBlank(message = "支付类型编号不能为空")
    private String code;

    @ApiModelProperty(value = "支付单描述信息", required = true)
    @NotBlank(message = "支付单描述信息不能为空")
    private String description;

}
