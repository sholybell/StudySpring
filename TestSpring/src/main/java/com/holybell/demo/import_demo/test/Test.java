package com.holybell.demo.import_demo.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        Dao dao = (Dao) applicationContext.getBean(Dao.class);
        dao.query();
    }
}
