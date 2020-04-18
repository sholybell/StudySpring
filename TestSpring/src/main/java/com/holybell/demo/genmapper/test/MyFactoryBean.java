package com.holybell.demo.genmapper.test;

import com.holybell.demo.genmapper.annotation.Select;
import com.holybell.demo.genmapper.dao.CardDao;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyFactoryBean implements FactoryBean {

    Class clazz;    // 为了令整个FactoryBean可以为各种Dao层接口生成动态代理对象

    public MyFactoryBean(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public Object getObject() throws Exception {
        Class[] clazzs = new Class[]{clazz};
        Object proxy = Proxy.newProxyInstance(this.getClass().getClassLoader(),
                clazzs,
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("成功的为CardDao生成了动态代理对象了哦~~~~");
                        // 这里仅仅简单的模拟了获取接口第一方法的标注了Select注解内的SQL
                        Method method1 = proxy.getClass().getInterfaces()[0].getMethod(method.getName());
                        Select select = method1.getDeclaredAnnotation(Select.class);
                        System.out.println("获取到了CardDao上的注解SQL : " + select.value());
                        return null;
                    }
                });
        return proxy;
    }

    @Override
    public Class<?> getObjectType() {
        return clazz;
    }
}
