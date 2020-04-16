package com.holybell.service;

import com.holybell.dao.UserDao;

public class UserServiceImpl implements UserService {

    // 这里是通过类型自动注入的
    UserDao dao;

    @Override
    public void find() {
        System.out.println("userServiceImpl find()");
    }
}
