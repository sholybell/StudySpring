package com.holybell.logdemo.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SpringLog {

    public static void main(String[] args) {
        Log log = LogFactory.getLog("SpringLog");
        log.info("SpringLog");
    }
}
