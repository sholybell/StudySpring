package com.holybell.dao;

public class UserDaoImpl implements UserDao {

    @Override
    public void query(String str) {
        System.out.println("模拟查询数据库:" + str);
    }

    @Override
    public String proxy() {
        return "~~~~~";
    }
}
