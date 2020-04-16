package com.holybell.aop.dao;

import com.holybell.aop.annotation.MyAopAnnotation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("prototype")
public class IndexDao implements Dao {

    public void query(String str) {
        System.out.println(str);
    }

    @MyAopAnnotation
    public void query() {
//        System.out.println(this.hashCode());
        System.out.println("IndexDao query()");
    }
}
