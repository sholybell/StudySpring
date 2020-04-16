package com.holybell.test;

import com.holybell.org.spring.util.AnnotationConfigBeanFactory;
import com.holybell.service.TestRegisterBean;

public class Test {

    public static void main(String[] args) {
        // 测试解析XML获取Bean对象
//        BeanFactory beanFactory = new BeanFactory("spring.xml");
//        UserService service = (UserService) beanFactory.getBean("service");
//        service.find();

        // 测试解析注解获取Bean对象
        AnnotationConfigBeanFactory beanFactory = new AnnotationConfigBeanFactory();
        // 配置扫描目录
        beanFactory.scan("com.holybell.service");
        // 从bean容器中获取注册好的对象
        TestRegisterBean registerBean = (TestRegisterBean) beanFactory.getObject("testRegisterBean");
        registerBean.print();
    }
}
