package com.foxwho.springbootdroolsdemo.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StockModel {
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
     * 库存数量
     */
    private Long num;
}
