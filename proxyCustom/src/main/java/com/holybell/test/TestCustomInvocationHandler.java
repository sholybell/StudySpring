package com.holybell.test;

import com.holybell.util.CustomInvocationHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestCustomInvocationHandler implements CustomInvocationHandler {
    Object target;

    public TestCustomInvocationHandler(Object target) {
        this.target = target;
    }

    public Object invoke(Method method, Object[] args) {
        try {
            System.out.println("--------------------------------"); // 这里就可以交给开发人员自定义自己的增强逻辑
            return method.invoke(target, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
