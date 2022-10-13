package com.ark.center.pay.module.paynotifyrecord.dto.response;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 支付结果通知记录
 * </p>
 *
 * @author EOP
 * @since 2022-08-11
 */
@Data
@ApiModel(value = "PayNotifyRecordRespDTO对象", description = "支付结果通知记录")
public class PayNotifyRecordRespDTO implements Serializable {

    @ApiModelProperty(value = "id", required = true)
    private Long id;

    @ApiModelProperty(value = "业务订单号", required = true)
    private String bizOrderCode;

    @ApiModelProperty(value = "支付订单号", required = true)
    private String payOrderCode;

    @ApiModelProperty(value = "通知请求体", required = true)
    private String reqBody;

    @ApiModelProperty(value = "支付状态 enums[PENDING_PAY,待支付,1;PAYING,支付中,2;PAY_SUCCESS,支付成功,3;PAY_FAIL,支付失败,4]", required = true)
    private Integer status;

}
