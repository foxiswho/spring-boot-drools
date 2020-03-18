package com.foxwho.springbootdroolsdemo.controller;

import cn.hutool.core.util.StrUtil;
import com.foxwho.springbootdroolsdemo.model.CartModel;
import com.foxwho.springbootdroolsdemo.service.CartProces;
import com.foxwho.springbootdroolsdemo.util.WrapperDrools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Slf4j
@RestController
public class CartController {

    @Autowired
    private CartProces cartProces;

    @GetMapping("/demo")
    public WrapperDrools index(Long goodsId, Long num, String name) {
        if (goodsId == null) {
            goodsId = 1L;
        }
        if (num == null) {
            num = 6L;
        }
        if (name == null) {
            name = "德国爱他美白金版1+段奶粉";
        }
        log.info("GET 参数,goodsId={},num={}", goodsId, num);
        //String format = StrUtil.format("最小购买数 不能小于 {} 或 最大购买数 不能大于 {}", 1, 5);
        //log.info("StrUtil.format ：{}",format);
        CartModel cartModel = new CartModel();
        cartModel.setGoodsId(goodsId);
        cartModel.setName(name);
        cartModel.setPrice(new BigDecimal("200"));
        cartModel.setNum(num);
        WrapperDrools process = cartProces.process(cartModel);
        return process;
    }
}
