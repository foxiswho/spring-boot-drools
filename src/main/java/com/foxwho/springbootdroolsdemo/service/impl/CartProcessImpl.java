package com.foxwho.springbootdroolsdemo.service.impl;

import com.foxwho.springbootdroolsdemo.drools.DroolsUtil;
import com.foxwho.springbootdroolsdemo.model.CartModel;
import com.foxwho.springbootdroolsdemo.model.GoodsModel;
import com.foxwho.springbootdroolsdemo.model.StockModel;
import com.foxwho.springbootdroolsdemo.service.CartProces;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.math.BigDecimal;

@Slf4j
@Service
public class CartProcessImpl implements CartProces {
    private static DroolsUtil droolsUtil;

    @Override
    public CartModel process(CartModel cartModel) {
        try {
            if (droolsUtil == null) {
                droolsUtil = DroolsUtil.getInstance();
                //加载规则
                droolsUtil.loadRuleResourcesFile("rules/cart.drl");
                droolsUtil.verify();

                log.info("INIT 11111 kieHelper");
                log.info("INIT 11111 kieHelper");
                log.info("INIT 11111 kieHelper");
            } else {
                log.info("NOT INIT kieHelper");
            }
            KieSession kieSession = droolsUtil.buildNewKieSession();
            FactHandle insert = kieSession.insert(cartModel);
            FactHandle insert1 = kieSession.insert(goods());
            FactHandle insert2 = kieSession.insert(stock());
            int i = kieSession.fireAllRules();
            log.info("规则 运行次数：{}, 商品名称: {}", i, cartModel.getName());
            //压测
            kieSession.delete(insert);
            kieSession.delete(insert1);
            kieSession.delete(insert2);
            kieSession.dispose();
        } catch (Exception e) {
            log.info("FileNotFoundException ={}", e.getMessage(), e);
        }
        return null;
    }

    /**
     * 商品
     *
     * @return
     */
    private GoodsModel goods() {
        GoodsModel goodsModel = new GoodsModel();
        goodsModel.setId(1L);
        goodsModel.setName("德国爱他美白金版1+段奶粉");
        goodsModel.setPrice(new BigDecimal("200"));
        goodsModel.setMax(20L);
        goodsModel.setMin(2L);
        return goodsModel;
    }

    /**
     * 库存
     *
     * @return
     */
    private StockModel stock() {
        StockModel stockModel = new StockModel();
        stockModel.setGoodsId(1L);
        stockModel.setName("德国爱他美白金版1+段奶粉");
        stockModel.setNum(10L);
        return stockModel;
    }
}
