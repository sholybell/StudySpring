package com.holybell.service;

import com.holybell.annotation.RegisterBean;

@RegisterBean("testRegisterBean")
public class TestRegisterBean {

    public void print() {
        System.out.println("成功获取到了注册的bean!!");
    }
}
