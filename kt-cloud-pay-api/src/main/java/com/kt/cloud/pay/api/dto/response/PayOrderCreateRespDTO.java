package com.kt.cloud.pay.api.dto.response;

import java.time.LocalDateTime;
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

    @ApiModelProperty(value = "业务订单号", required = true)
    @NotEmpty(message = "业务订单号不能为空")
    private String bizOrderCode;

    @ApiModelProperty(value = "支付类型编码", required = true)
    @NotEmpty(message = "支付类型编码不能为空")
    private String payTypeCode;

}
