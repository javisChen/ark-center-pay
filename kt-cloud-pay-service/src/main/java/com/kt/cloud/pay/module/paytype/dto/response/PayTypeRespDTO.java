package com.kt.cloud.pay.module.paytype.dto.response;

import java.time.LocalDateTime;
import java.io.Serializable;
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
@ApiModel(value = "PayTypeRespDTO对象", description = "支付类别表")
public class PayTypeRespDTO implements Serializable {

    @ApiModelProperty(value = "id", required = true)
    private Long id;

    @ApiModelProperty(value = "支付类型名称", required = true)
    private String name;

    @ApiModelProperty(value = "支付类型编号", required = true)
    private String code;

    @ApiModelProperty(value = "支付单描述信息", required = true)
    private String description;

}
