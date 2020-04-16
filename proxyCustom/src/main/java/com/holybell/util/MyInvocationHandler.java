package com.holybell.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

    Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     * @param proxy  代理对象
     * @param method 被代理的目标方法
     * @param args   被代理的目标方法参数
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("MyInvocationHandler call....");
        return method.invoke(target, args);
    }
}
