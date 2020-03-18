package com.foxwho.springbootdroolsdemo.service;

import com.foxwho.springbootdroolsdemo.model.CartModel;
import com.foxwho.springbootdroolsdemo.util.WrapperDrools;

public interface CartProces {
    WrapperDrools process(CartModel cartModel);
}
