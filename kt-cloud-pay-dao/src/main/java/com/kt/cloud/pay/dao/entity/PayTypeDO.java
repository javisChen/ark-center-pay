package com.kt.cloud.pay.dao.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kt.component.orm.mybatis.base.BaseEntity;
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
 * 支付类别表
 * </p>
 *
 * @author EOP
 * @since 2022-08-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("pa_pay_type")
public class PayTypeDO extends BaseEntity {


    /**
     * 支付类型名称
     */
    @TableField("`name`")
    private String name;

    /**
     * 支付类型编号
     */
    @TableField("`code`")
    private String code;

    /**
     * 支付单描述信息
     */
    @TableField("`description`")
    private String description;

}
