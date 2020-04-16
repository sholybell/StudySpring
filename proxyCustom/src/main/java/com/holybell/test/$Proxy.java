package com.holybell.test;

import com.holybell.dao.TestDao;
import com.holybell.dao.TestDaoImpl;
import com.holybell.util.CustomInvocationHandler;

import java.lang.Exception;
import java.lang.reflect.Method;

public class $Proxy implements TestDao {
    private CustomInvocationHandler h;

    public $Proxy(CustomInvocationHandler h) {
        this.h = h;
    }

    public String query(String p) throws Exception {
        Method method = Class.forName("com.holybell.dao.TestDao").getDeclaredMethod("query", new Class[]{String.class});

        Object[] args = new Object[]{p};
        return (String) h.invoke(method, args);
    }

    public static void main(String[] args) throws Exception {
        $Proxy proxy = new $Proxy(new TestCustomInvocationHandler(new TestDaoImpl()));
        proxy.query("str");
    }
}