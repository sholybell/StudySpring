package com.holybell.demo.genmapper.test;

import com.holybell.demo.genmapper.config.AppConfig;
import com.holybell.demo.genmapper.dao.CardDao;
import com.holybell.demo.genmapper.service.CardService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // 我并没有提供CarDao的实现类，但是成功的从Spring中获取到了CardDao的实现类
        CardDao cardDao = (CardDao) applicationContext.getBean("cardDao");
        cardDao.showCard();
        // 这里成功的获取到了注入了没有实现CardDao接口的CardService对象
        CardService cardService = applicationContext.getBean(CardService.class);
        cardService.test();
    }
}
