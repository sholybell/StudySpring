package com.holybell.demo.genmapper.test;

import com.holybell.demo.genmapper.dao.CardDao;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // 得到BeanDefinition
        // 扫描所有的接口，这里的CarDao可以通过扫描获取到Mybatis对应的Mapper文件夹下对应的Mapper接口
//        CardDao dao = (CardDao) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{CardDao.class}, new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                return null;
//            }
//        });

        // 注意这里通过动态代理生成的dao，使用dao.class不会是CardDao.class,应该是CGLIB的子类，或者JDK动态代理的Proxy
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(CardDao.class);   // CardDao.class可以通过扫描包获取类名
        GenericBeanDefinition beanDefinition = (GenericBeanDefinition) beanDefinitionBuilder.getBeanDefinition();   // 此时BeanDefinition中的beanClass是 CardDao.class,它是一个接口,Spring并无法实例化
        // CardDao 接口无法被 Spring 初始化，那么使用 FactoryBean 类来创建 CardDao 的动态代理
        beanDefinition.setBeanClass(MyFactoryBean.class);
        // 强制指定BeanDefinition对象中的构造函数信息,Spring只会根据BeanDefinition中的类和构造器信息进行bean的生成，这里就可以对BeanDefinition改造
        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue("com.holybell.demo.genmapper.dao.CardDao");
        registry.registerBeanDefinition("cardDao", beanDefinition);
    }
}
