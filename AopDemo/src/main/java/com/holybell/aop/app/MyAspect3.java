package com.holybell.aop.app;

import com.holybell.aop.dao.Dao;
import com.holybell.aop.dao.IndexDao;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

/**
 * Introduction举例
 * 很少使用
 */
//@Component
@Aspect
public class MyAspect3 {

    /**
     * 找到value包下面的类，引入defaultImpl类实现的方法，实现的接口是Dao接口
     * 在这里，其实实现的是令{@link com.holybell.aop.dao.OrderDao}实现了{@link Dao}
     * 实现的逻辑采用的是{@link IndexDao}实现的逻辑
     */
    @DeclareParents(value = "com.holybell.aop.dao.*", defaultImpl = IndexDao.class)
    public static Dao dao;

}
