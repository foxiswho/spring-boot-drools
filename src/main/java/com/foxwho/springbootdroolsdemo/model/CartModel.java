package com.foxwho.springbootdroolsdemo.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartModel {
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

    private int next = 0;
}
