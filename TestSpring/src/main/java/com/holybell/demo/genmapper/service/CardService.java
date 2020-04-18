package com.holybell.demo.genmapper.service;

import com.holybell.demo.genmapper.dao.CardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    private CardDao cardDao;        // 通过动态生成代理，令Spring可以注入没有被实现的CardDao接口

    public void test() {
        System.out.println("----------------->");
        cardDao.showCard();
    }
}
