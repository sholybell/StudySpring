package com.holybell.util;

import java.lang.reflect.Method;

public interface CustomInvocationHandler {

    public Object invoke(Method method, Object args[]);
}
