package com.kt.cloud.pay.api.dto.response;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 支付订单信息
 * </p>
 *
 * @author EOP
 * @since 2022-08-11
 */
@Data
@ApiModel(value = "PayOrderCreateRespDTO", description = "支付订单信息")
public class PayOrderCreateRespDTO implements Serializable {

    @ApiModelProperty(value = "支付单ID", required = true)
    private Long payOrderId;

    @ApiModelProperty(value = "业务订单号", required = true)
    private String bizTradeNo;

    @ApiModelProperty(value = "支付类型编码", required = true)
    private String payTypeCode;

}
