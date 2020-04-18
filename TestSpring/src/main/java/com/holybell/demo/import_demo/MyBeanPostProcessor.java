package com.holybell.demo.import_demo;

import com.holybell.demo.import_demo.test.Dao;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Proxy;

/**
 * 实现 BeanPostProcessor , 结合 Import 注解，这样就可以启动在Bean的生命周期生成某个bean的代理对象
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // 为indexDao这个bean生成代理对象返回
        if (beanName.equals("indexDao")) {
            bean = Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{Dao.class}, new MyInvocationHandler(bean));
        }
        return bean;
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }
}
