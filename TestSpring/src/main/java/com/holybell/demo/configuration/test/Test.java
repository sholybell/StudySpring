package com.holybell.demo.configuration.test;

import com.holybell.demo.configuration.config.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        Config config = applicationContext.getBean(Config.class);   // 如果使用了@Configuration 这里会去到的配置类会被CGLIB代理


        System.out.println("~~~~");
    }
}
