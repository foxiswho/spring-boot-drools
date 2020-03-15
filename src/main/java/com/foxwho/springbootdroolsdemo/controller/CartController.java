package com.foxwho.springbootdroolsdemo.controller;

import com.foxwho.springbootdroolsdemo.model.CartModel;
import com.foxwho.springbootdroolsdemo.service.CartProces;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Slf4j
@RestController
public class CartController {

    @Autowired
    private CartProces cartProces;

    public String index() {
        CartModel cartModel = new CartModel();
        cartModel.setGoodsId(1L);
        cartModel.setName("德国爱他美白金版1+段奶粉");
        cartModel.setPrice(new BigDecimal("200"));
        cartModel.setNum(6L);
        cartProces.process(cartModel);
        return "ok";
    }
}
