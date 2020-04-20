package com.holybell.logdemo.test;

import java.util.logging.Logger;

public class JUL {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger("jul");
        logger.info("jul log");
    }
}
