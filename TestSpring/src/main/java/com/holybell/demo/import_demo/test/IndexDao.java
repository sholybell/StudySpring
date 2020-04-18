package com.holybell.demo.import_demo.test;

import org.springframework.stereotype.Component;

@Component
public class IndexDao implements Dao {
    @Override
    public void query() {
        System.out.printf("I am a IndexDao !");
    }
}
