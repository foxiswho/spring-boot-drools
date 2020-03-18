package com.foxwho.springbootdroolsdemo.drools;

import lombok.extern.slf4j.Slf4j;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;

import java.io.InputStream;

@Slf4j
public class DroolsUtil {

    private KieHelper kieHelper = new KieHelper();

    public void DroolsUtil() {

    }

    public KieHelper getKieHelper() {
        return kieHelper;
    }

    public static DroolsUtil getInstance() {
        return new DroolsUtil();
    }

    /**
     * 读取 规则 文件, Resources 目录下文件
     *
     * @param file
     * @return
     */
    public DroolsUtil loadRuleResourcesFile(String file) {
        log.info("rule file ={}", file);
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
        log.info("inputStream2 ={}", inputStream);
        Resource resource = ResourceFactory.newInputStreamResource(inputStream);
        kieHelper.addResource(resource, ResourceType.DRL);
        return this;
    }

    /**
     * 读取规则内容
     *
     * @param content
     * @return
     */
    public DroolsUtil loadRuleContent(String content) {
        log.info("rule content ={}", content);
        kieHelper.addContent(content, ResourceType.DRL);
        return this;
    }

    /**
     * 读取规则Url
     *
     * @param url
     * @return
     */
    public DroolsUtil loadRuleUrl(String url) {
        log.info("rule url ={}", url);
        kieHelper.addResource(ResourceFactory.newUrlResource(url), ResourceType.DRL);
        return this;
    }

    /**
     * 验证
     *
     * @return
     */
    public KieHelper verify() {
        try {
            Results results = kieHelper.verify();
            if (results.hasMessages(Message.Level.ERROR)) {
                log.error("rule error ={}", results.getMessages());
                throw new IllegalStateException("rule error: " + results.getMessages());
            }
            return kieHelper;
        } catch (Exception e) {
            log.info("FileNotFoundException ={}", e.getMessage(), e);
        }
        return null;
    }

    /**
     * 编译
     *
     * @return
     */
    public KieSession buildNewKieSession() {
        return kieHelper.build().newKieSession();
    }

    public static void demo() {
        /*

        //购物车
        CartModel cartModel = new CartModel();
        cartModel.setGoodsId(1L);
        cartModel.setName("德国爱他美白金版1+段奶粉");
        cartModel.setPrice(new BigDecimal("200"));
        cartModel.setNum(6L);
        //商品
        GoodsModel goodsModel = new GoodsModel();
        goodsModel.setId(1L);
        goodsModel.setName("德国爱他美白金版1+段奶粉");
        goodsModel.setPrice(new BigDecimal("200"));
        goodsModel.setMax(20L);
        goodsModel.setMin(2L);
        //库存
        StockModel stockModel = new StockModel();
        stockModel.setGoodsId(1L);
        stockModel.setName("德国爱他美白金版1+段奶粉");
        stockModel.setNum(10L);
        //初始化
        DroolsUtil droolsUtil = DroolsUtil.getInstance();
        //读取规则问题件
        droolsUtil.loadRuleResourcesFile("rules/cart.drl");
        //验证
        droolsUtil.verify();
        //
        KieSession kieSession = droolsUtil.buildNewKieSession();
        //插入 对象，获取 对象所对应的内存句柄
        FactHandle insert = kieSession.insert(cartModel);
        FactHandle insert1 = kieSession.insert(goodsModel);
        FactHandle insert2 = kieSession.insert(stockModel);
        int i = kieSession.fireAllRules();
        log.info("规则 运行次数：{}, 商品名称: {}", i, cartModel.getName());
        //删除 fact 内存句柄
        kieSession.delete(insert);
        kieSession.delete(insert1);
        kieSession.delete(insert2);
        //清除 释放资源
        kieSession.dispose();

        */
    }

    public void dispose() {
        kieHelper = null;
    }
}
