package com.foxwho.springbootdroolsdemo.service;

import com.foxwho.springbootdroolsdemo.model.CartModel;
import com.foxwho.springbootdroolsdemo.model.GoodsModel;
import com.foxwho.springbootdroolsdemo.util.WrapperDrools;

public interface GoodsProces {
    WrapperDrools process(GoodsModel goodsModel);
}
