package com.foxwho.springbootdroolsdemo.service.impl;

import com.foxwho.springbootdroolsdemo.drools.DroolsUtil;
import com.foxwho.springbootdroolsdemo.model.GoodsModel;
import com.foxwho.springbootdroolsdemo.service.GoodsProces;
import com.foxwho.springbootdroolsdemo.util.WrapperDrools;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class GoodsProcessImpl implements GoodsProces {
    /**
     * 实例化日志 对象，方便 drools 内部使用
     */
    private static final Logger LOG = LoggerFactory.getLogger(GoodsProcessImpl.class);

    private static DroolsUtil droolsUtil;

    @Override
    public WrapperDrools process(GoodsModel goodsModel) {
        // 初始化 返回值对象
        WrapperDrools wrapperDrools = new WrapperDrools();
        try {
            //检测 droolsUtil 是否已实例化，如果没有则 进行 实例化相关操作
            if (droolsUtil == null) {
                //实例化
                droolsUtil = DroolsUtil.getInstance();
                //加载规则
                droolsUtil.loadRuleResourcesFile("rules/goods.drl");
                //如果 规则发生 错误，会直接 跳到 catch 那里
                droolsUtil.verify();

                log.info("INIT 11111 kieHelper");
                log.info("INIT 11111 kieHelper");
                log.info("INIT 11111 kieHelper");
            } else {
                log.info("NOT INIT kieHelper");
            }
            //返回 加载规则后的 kieSession
            KieSession kieSession = droolsUtil.buildNewKieSession();

            log.info("数据：{}", goodsModel);
            //插入 对象，获取 对象所对应的内存句柄

            FactHandle insert1 = kieSession.insert(goodsModel);
            //
            log.info("wrapperDrools: {}", wrapperDrools);
            // 传入 全局公共 service 类对象
            // 传入，返回值类，日志类
            kieSession.setGlobal("wrapperDrools", wrapperDrools);
            kieSession.setGlobal("LOG", LOG);
            // 执行 所有规则，并返回 执行条数
            int i = kieSession.fireAllRules();
            log.info("规则 运行次数：{}, 商品名称: {}", i, goodsModel.getName());
            //删除 fact 内存句柄
            kieSession.delete(insert1);
            //清除 释放资源
            kieSession.dispose();
            log.info("wrapperDrools 返回结果: {}", wrapperDrools);

        } catch (Exception e) {
            log.info("FileNotFoundException ={}", e.getMessage(), e);
            wrapperDrools.error("规则出错");
        }
        return wrapperDrools;
    }
}
