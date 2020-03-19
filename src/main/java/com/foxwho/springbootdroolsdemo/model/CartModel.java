package com.foxwho.springbootdroolsdemo.model;

import cn.hutool.core.builder.EqualsBuilder;
import cn.hutool.core.builder.HashCodeBuilder;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CartModel implements Serializable {
    /**
     * id
     */
    private Long id;
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 名称
     */
    private String name;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 数量
     */
    private Long num;
    /**
     * 因为没有找到 怎么停止 不在执行后续规则的方法，所以这里 取了一个巧
     * 这里是 配合 drools 不在执行后续规则 的额外参数
     * true 可以继续执行后续规则， false 不在执行后续规则
     */
    private boolean next = true;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
