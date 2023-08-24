package com.ark.center.pay.module.paytype.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ark.component.dto.PagingQuery;
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
@Schema(description = "PayTypePageQueryReqDTO对象")
public class PayTypePageQueryReqDTO extends PagingQuery {


    @Schema(description = "支付类型名称")
    private String name;

    @Schema(description = "支付类型编号")
    private String code;

    @Schema(description = "支付单描述信息")
    private String description;

}
