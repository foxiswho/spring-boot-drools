package com.foxwho.springbootdroolsdemo.drools;

import lombok.extern.slf4j.Slf4j;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class DroolsUtil {
    //private static KnowledgeBuilder builder= KnowledgeBuilderFactory.newKnowledgeBuilder();
    //private KieBase kieBase;
    //private static KieHelper kieHelper = new KieHelper();

    public void xx() throws IOException {
        /**
         * 本地加载DRL
         */
        //Resource resourceNative = ResourceFactory.newFileResource("cart.drl");
        /**
         * 远程加载URL
         */
        String url = "http://localhost:9999/remote.drl";
        //Resource resourceRemote = ResourceFactory.newUrlResource(url);
        //log.info("resourceRemote={}",resourceRemote);
// 加载方式的不同
//        builder.add(resourceNative, ResourceType.DRL);
//        builder.add(resourceRemote, ResourceType.DRL);
//        kieBase = builder.newKieBase();
//        KieSession kieSession = kieBase.newKieSession();
        //Person person = new Person();
        //
        //person.setAge(12);
        //person.setName("这是姓名");
        //kieSession.insert(person);
        //kieSession.fireAllRules();
        //kieSession.dispose();


        String file = "rule/cart.drl";
        //FileInputStream fileInputStream = new FileInputStream(file);
        //Resource resource2 = new ClassPathResource(file);
        //InputStream fileInputStream = resource2.getInputStream();
        InputStream fileInputStream = this.getClass().getResourceAsStream(file);
        KieHelper kieHelper = new KieHelper();
        Resource resource = ResourceFactory.newInputStreamResource(fileInputStream);
        kieHelper.addResource(resource, ResourceType.DRL);

        Results results = kieHelper.verify();
        if (results.hasMessages(Message.Level.ERROR)) {
            System.out.println(results.getMessages());
            throw new IllegalStateException("### errors ###");
        }

        StatelessKieSession kieSession = kieHelper.build().newStatelessKieSession();
        //kieSession.insert(person);
        //kieSession.fireAllRules();
        //kieSession.dispose();
    }
}
