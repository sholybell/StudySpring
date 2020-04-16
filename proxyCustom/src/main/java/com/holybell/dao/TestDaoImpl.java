package com.holybell.dao;

public class TestDaoImpl implements TestDao {

    public String query(String str) {
        return "TestDaoImpl.query() :" + str;
    }
}
