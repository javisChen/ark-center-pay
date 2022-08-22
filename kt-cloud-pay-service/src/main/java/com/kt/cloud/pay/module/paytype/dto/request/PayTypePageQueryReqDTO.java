package com.kt.cloud.pay.module.paytype.dto.request;

import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.kt.component.dto.PagingQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 支付类别表
 * </p>
 *
 * @author EOP
 * @since 2022-08-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PayTypePageQueryReqDTO对象", description = "支付类别表")
public class PayTypePageQueryReqDTO extends PagingQuery {


    @ApiModelProperty(value = "支付类型名称")
    private String name;

    @ApiModelProperty(value = "支付类型编号")
    private String code;

    @ApiModelProperty(value = "支付单描述信息")
    private String description;

}
