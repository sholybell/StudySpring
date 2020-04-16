package com.holybell.aop.app;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 环绕通知举例
 */
@Component
//@Scope("prototype")
//@Aspect("perthis(this(com.syb.study.spring.aop.demo.dao.IndexDao))")      // 指定当代理对象是com.holybell.aop.dao.IndexDao类型的时候，切面类使用单例模式
@Aspect
public class MyAspect2 {

    @Pointcut("within(com.holybell.aop.dao.*)")
    public void pointCutAround() {
    }

//    @Before("pointCutAround()")
//    public void before(JoinPoint joinPoint) {
//        System.out.println("before");
//        System.out.println("this:" + joinPoint.getThis());
//        System.out.println("target:" + joinPoint.getTarget());
//    }
//
//    @After("pointCutAround()")
//    public void after() {
//        System.out.println("after");
//    }

    /**
     * @param pjp 表示就是当前正在被增强的方法，连接点被当做了对象，做了方法入参
     */
    @Around("pointCutAround()")
    public void around(ProceedingJoinPoint pjp) {
//        System.out.println(this.hashCode());
        Object[] args = pjp.getArgs();
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                args[i] += " 环绕通知增加的内容~~~~";
            }
        }
        System.out.println("before");
        try {
            pjp.proceed(args);  // 执行下一个通知，或者执行被增强的方法
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("after");
    }

}
