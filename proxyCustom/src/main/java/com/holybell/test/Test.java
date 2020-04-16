package com.holybell.test;

import com.holybell.dao.TestDao;
import com.holybell.dao.TestDaoImpl;
import com.holybell.dao.UserDao;
import com.holybell.dao.UserDaoImpl;
import com.holybell.proxy.ProxyUtil2;
import com.holybell.util.CustomInvocationHandler;
import com.holybell.util.MyInvocationHandler;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {

    public static void main(String[] args) {
        // 自己实现的动态代理逻辑
//        UserDao dao =(UserDao) ProxyUtil.newInstance(new UserDaoImpl());
//        dao.query();
//        System.out.println(dao.proxy());    // 存在返回值

        // JDK动态代理
//        UserDao dao = (UserDao) Proxy.newProxyInstance(
//                Test.class.getClassLoader(),    // 类加载器
//                new Class[]{UserDao.class},     // 要被代理的接口集合
//                new MyInvocationHandler(new UserDaoImpl())  // 被代理的执行逻辑，需要额外传入被代理的目标对象，执行逻辑才能通过目标对象调用目标方法
//        );
//        dao.query("key");
//        dao.proxy();


        // 模拟JDK的方式生成动态代理类
        TestDao dao = (TestDao) ProxyUtil2.newInstance(TestDao.class,
                new TestCustomInvocationHandler(new TestDaoImpl()));
        try {
            System.out.println(dao.query("test"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 上面的代码执行完毕会生成如下的代码文件:
//    public class $Proxy implements TestDao{
//        private CustomInvocationHandler h;
//        public $Proxy (CustomInvocationHandler h){
//            this.h = h;
//        }
//        public String query(String p)throws Exception {
//            Class[] clazzs = new Class[]{String.class};
//            Method method = Class.forName("com.holybell.dao.TestDao").getDeclaredMethod("query",clazzs);
//            Object[] args = new Object[]{p};
//            return (String)h.invoke(method,args);
//        }
//    }
}
