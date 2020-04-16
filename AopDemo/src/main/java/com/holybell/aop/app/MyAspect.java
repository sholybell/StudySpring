package com.holybell.aop.app;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 各种指定切点的关键字:
 * execution\within\this\target\args\@target\@args\@within\@annotation
 */
//@Component
@Aspect
public class MyAspect {

    /**
     * 切点：表示一批连接点的组合
     * execution可以做更精确的指定，访问权限，返回类型，入参等等
     */
    @Pointcut("execution(* com.holybell.aop.dao.*.*(..))")
    public void pointCut() {

    }

    /**
     * within只能做用到包的范围
     */
    @Pointcut("within(com.holybell.aop.dao.*)")
    public void pointCutWithin() {

    }

    /**
     * 直接指定方法的限定参数类型,不管在哪个包，只需要入参类型符合，即被增强
     */
    @Pointcut("args(java.lang.String)")
    public void pointCutArgs() {

    }

    /**
     * 直接使用注解标识哪些方法需要使用切面增强
     */
    @Pointcut("@annotation(com.holybell.aop.annotation.MyAopAnnotation)")
    public void pointCutAnnotation() {

    }

    /**
     * 代理对象是否com.syb.study.spring.aop.demo.dao.IndexDao类或者派生类， 是就可以增强
     */
    @Pointcut("this(com.holybell.aop.dao.IndexDao)")
    public void pointCutThis() {

    }

    /**
     * 代理对象使用的是JDK接口动态代理，并不是com.syb.study.spring.aop.demo.dao.IndexDao类或者子类
     * 于是强制指定被代理的对象是com.syb.study.spring.aop.demo.dao.IndexDao类的对象
     *
     * 简单来说：代理对象 instanceof com.syb.study.spring.aop.demo.dao.IndexDao是false的
     */
    @Pointcut("target(com.holybell.aop.dao.IndexDao)")
    public void pointCutTarget() {

    }

    //    @Before("pointCut()") // 使用切点
    //    @Before("pointCutWithin()")
//    @Before("!pointCutArgs() && pointCutWithin()")  // 组合使用切点，这里采用符合后者的集合，排除了前者的
    @Before("pointCutAnnotation()")
    public void before() {
        System.out.println("before");
    }
}
