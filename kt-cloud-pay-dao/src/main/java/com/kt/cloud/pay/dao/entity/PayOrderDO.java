package com.kt.cloud.pay.dao.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kt.component.orm.mybatis.base.BaseEntity;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.kt.component.common.enums.BasicEnums;
import com.kt.component.common.enums.EnumUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
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
@TableName("pa_pay_order")
public class PayOrderDO extends BaseEntity {


    /**
     * 业务订单号
     */
    @TableField("biz_order_code")
    private String bizOrderCode;

    /**
     * 支付订单号
     */
    @TableField("pay_order_code")
    private String payOrderCode;

    /**
     * 支付类型编码
     */
    @TableField("pay_type_code")
    private String payTypeCode;

    /**
     * 支付金额
     */
    @TableField("amount")
    private Integer amount;

    /**
     * 支付单描述信息
     */
    @TableField("`description`")
    private String description;

    /**
     * 支付状态 enums[PENDING_PAY,待支付,1;PAYING,支付中,2;PAY_SUCCESS,支付成功,3;PAY_FAIL,支付失败,4]
     */
    @TableField("`status`")
    private Integer status;

    /**
     * 最后一次支付结果通知时间
     */
    @TableField("last_notify_time")
    private LocalDateTime lastNotifyTime;

    @Getter
    @AllArgsConstructor
    public enum Status implements BasicEnums {
        PENDING_PAY(1, "待支付"),
            PAYING(2, "支付中"),
            PAY_SUCCESS(3, "支付成功"),
            PAY_FAIL(4, "支付失败"),
    ;
        private final Integer value;
        private final String text;

        public static Status getByValue(Integer value) {
            return EnumUtils.getByValue(values(), value);
        }

        public static String getText(Integer value) {
            return EnumUtils.getTextByValue(values(), value);
        }
    }
}