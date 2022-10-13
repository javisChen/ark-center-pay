package com.ark.center.pay.api.dto.request;

import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ark.component.dto.PagingQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 支付订单表
 * </p>
 *
 * @author EOP
 * @since 2022-08-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PayOrderPageQueryReqDTO对象", description = "支付订单表")
public class PayOrderPageQueryReqDTO extends PagingQuery {

    @ApiModelProperty(value = "业务订单号")
    private String bizOrderCode;

    @ApiModelProperty(value = "支付订单号")
    private String payOrderCode;

    @ApiModelProperty(value = "支付类型编码")
    private String payTypeCode;

    @ApiModelProperty(value = "支付金额")
    private Integer amount;

    @ApiModelProperty(value = "支付单描述信息")
    private String description;

    @ApiModelProperty(value = "支付状态 enums[PENDING_PAY,待支付,1;PAYING,支付中,2;PAY_SUCCESS,支付成功,3;PAY_FAIL,支付失败,4]")
    private Integer status;

    @ApiModelProperty(value = "最后一次支付结果通知时间")
    private LocalDateTime lastNotifyTime;

}
