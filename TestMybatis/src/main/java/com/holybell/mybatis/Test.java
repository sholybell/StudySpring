package com.holybell.mybatis;

import com.holybell.mybatis.config.Config;
import com.holybell.mybatis.service.SendExpressAgentServiceService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

    public static void main(String[] args) {
//        org.apache.ibatis.logging.LogFactory.useLog4JLogging();   // 配合mybatis+log4j+Spring5开启debug日志
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        SendExpressAgentServiceService service = applicationContext.getBean(SendExpressAgentServiceService.class);
        service.print();
    }
}
