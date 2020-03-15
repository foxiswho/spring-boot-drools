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
}
