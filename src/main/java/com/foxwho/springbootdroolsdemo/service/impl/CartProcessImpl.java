package com.foxwho.springbootdroolsdemo.service.impl;

import com.foxwho.springbootdroolsdemo.model.CartModel;
import com.foxwho.springbootdroolsdemo.model.GoodsModel;
import com.foxwho.springbootdroolsdemo.model.StockModel;
import com.foxwho.springbootdroolsdemo.service.CartProces;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieBase;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;

@Slf4j
@Service
public class CartProcessImpl implements CartProces {
    @Override
    public CartModel process(CartModel cartModel) {

        String file = "rule/cart.drl";

        KieHelper kieHelper = new KieHelper();
        try {
            ClassPathResource classPathResource = new ClassPathResource(file);
            //InputStream inputStream2 =classPathResource.getInputStream();
            //log.info("inputStream1 ={}",inputStream2);
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
            log.info("inputStream2 ={}",inputStream);
            //InputStream fileInputStream = this.getClass().getResourceAsStream(file);
            //log.info("fileInputStream3 ={}",fileInputStream);
            Resource resource = ResourceFactory.newInputStreamResource(inputStream);
            kieHelper.addResource(resource, ResourceType.DRL);
            //kieHelper.addContent("",ResourceType.DRL);

            Results results = kieHelper.verify();
            if (results.hasMessages(Message.Level.ERROR)) {
                System.out.println(results.getMessages());
                log.error("rule error ={}", results.getMessages());
                throw new IllegalStateException("### errors ###");
            }

            //KieBase build = kieHelper.build();

            //StatelessKieSession kieSession = kieHelper.build().newStatelessKieSession();
            //StatelessKieSession statelessKieSession = build.newStatelessKieSession();
            //KieSession kieSession2 = build.newKieSession();
            KieSession kieSession = kieHelper.build().newKieSession();
            FactHandle insert = kieSession.insert(cartModel);
            FactHandle insert1 = kieSession.insert(goods());
            FactHandle insert2 = kieSession.insert(stock());
            int i = kieSession.fireAllRules();
            System.out.println(cartModel.getName() + "    " + i + "次");
            //压测
            kieSession.delete(insert);
            kieSession.delete(insert1);
            kieSession.delete(insert2);
            kieSession.dispose();
        } catch (Exception e) {
            //e.printStackTrace();
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
