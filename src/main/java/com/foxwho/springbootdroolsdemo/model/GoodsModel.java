package com.foxwho.springbootdroolsdemo.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsModel {
    /**
     * id
     */
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 最小购买数量
     */
    private Long min;

    /**
     * 最大购买数量
     */
    private Long max;
    /**
     * 因为没有找到 怎么停止 不在执行后续规则的方法，所以这里 取了一个巧
     * 这里是 配合 drools 不在执行后续规则 的额外参数
     * true 可以继续执行后续规则， false 不在执行后续规则
     */
    private boolean next = true;
}
