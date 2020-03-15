package com.foxwho.springbootdroolsdemo.service.impl;

import com.foxwho.springbootdroolsdemo.SpringBootDroolsDemoApplication;
import com.foxwho.springbootdroolsdemo.model.CartModel;
import com.foxwho.springbootdroolsdemo.service.CartProces;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(SpringExtension.class) //导入spring测试框架
@SpringBootTest(classes = {SpringBootDroolsDemoApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CartProcessImplTest {

    @Autowired
    private CartProces cartProces;

    @Test
    void process() {
        CartModel cartModel = new CartModel();
        cartModel.setGoodsId(1L);
        cartModel.setName("德国爱他美白金版1+段奶粉");
        cartModel.setPrice(new BigDecimal("200"));
        cartModel.setNum(6L);
        cartProces.process(cartModel);
    }
}