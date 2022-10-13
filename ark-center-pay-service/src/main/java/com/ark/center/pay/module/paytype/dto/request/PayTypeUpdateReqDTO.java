package com.ark.center.pay.module.paytype.dto.request;

import java.time.LocalDateTime;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
@ApiModel(value = "PayTypeUpdateReqDTO对象", description = "支付类别表")
public class PayTypeUpdateReqDTO implements Serializable {

    @ApiModelProperty(value = "id", required = true)
    @NotNull(message = "id不能为空")
    private Long id;

    @ApiModelProperty(value = "支付类型名称", required = true)
    @NotEmpty(message = "支付类型名称不能为空")
    private String name;

    @ApiModelProperty(value = "支付类型编号", required = true)
    @NotEmpty(message = "支付类型编号不能为空")
    private String code;

    @ApiModelProperty(value = "支付单描述信息", required = true)
    @NotEmpty(message = "支付单描述信息不能为空")
    private String description;

}