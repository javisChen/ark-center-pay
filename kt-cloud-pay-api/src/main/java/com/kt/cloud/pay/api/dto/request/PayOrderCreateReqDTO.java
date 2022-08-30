package com.kt.cloud.pay.api.dto.request;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "PayOrderCreateReqDTO", description = "生成支付单")
public class PayOrderCreateReqDTO implements Serializable {

    @ApiModelProperty(value = "业务订单号", required = true)
    @NotEmpty(message = "业务订单号不能为空")
    private String bizTradeNo;

    @ApiModelProperty(value = "支付类型编码", required = true)
    @NotEmpty(message = "支付类型编码不能为空")
    private String payTypeCode;

    @ApiModelProperty(value = "支付金额", required = true)
    @NotNull(message = "支付金额不能为空")
    private Integer amount;

    @ApiModelProperty(value = "支付单描述信息", required = true)
    @NotEmpty(message = "支付单描述信息不能为空")
    private String description;

}