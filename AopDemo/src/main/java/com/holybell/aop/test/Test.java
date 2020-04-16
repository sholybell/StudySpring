package com.holybell.aop.test;

import com.holybell.aop.app.Config;
import com.holybell.aop.dao.Dao;
import com.holybell.aop.dao.IndexDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Config.class);
        Dao dao = annotationConfigApplicationContext.getBean(IndexDao.class);
//        dao.query("test");
//        System.out.println("--------------------->");
        dao.query();
        System.out.println("============================");

//        Dao orderDao = (Dao) annotationConfigApplicationContext.getBean("orderDao");
//        orderDao.query();
        dao = annotationConfigApplicationContext.getBean(IndexDao.class);
        dao.query();
    }
}
