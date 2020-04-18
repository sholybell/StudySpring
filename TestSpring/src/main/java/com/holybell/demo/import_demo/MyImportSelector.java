package com.holybell.demo.import_demo;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 使用Spring提供的ImportSelector注入沒被Spring扫描到的类
 *
 * 这个类的使用场景:
 * 联想Spring提供的各种启用某种功能的注解，@EnableXxxxxxx，这些注解都组合了这个@Import注解
 * 平时那些类是不会被Spring扫描加载到环境中的
 * 当程序员想打开某个功能的时候，在启动配置类上加上这个注解，就会令Spring扫描到这个类，从而启动某种功能，
 *
 */
public class MyImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{MyBeanPostProcessor.class.getName()};
    }
}
