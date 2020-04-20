package com.holybell.logdemo.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 这其实是一个门面工具，也就是说它本身并不会做日志的输出，也是通过它作为门槛，它会去找到具体的日志输出框架输出
 * <p>
 * <dependency>
 * <groupId>log4j</groupId>
 * <artifactId>log4j</artifactId>   这个框架执行了日志输出逻辑
 * <version>1.2.17</version>
 * </dependency>
 * <p>
 * <dependency>
 * <groupId>commons-logging</groupId>
 * <artifactId>commons-logging</artifactId>
 * <version>1.2</version>
 * </dependency>
 * <p>
 * JCL查找具体日志输出框架逻辑为：
 * 在循环中执行 Class.forName() ，没找到的捕获异常，进行下一轮循环，
 * 查找下面是个借口是否存在，前面找到就直接返回前面的日志框架
 * <p>
 * org.apache.commons.logging.impl.Log4JLogger
 * org.apache.commons.logging.impl.Jdk14Logger
 * org.apache.commons.logging.impl.Jdk13LumberjackLogger
 * org.apache.commons.logging.impl.SimpleLog
 * <p>
 * 这么做的好处主要是，引入了JCL之后，可以在后期方便的替换具体的日志框架，只要是上面4个框架
 * JCL都能通过统一的接口，输出日志，不需要更改整个项目的输出逻辑
 */
public class JCL {

    public static void main(String[] args) {
        Log log = LogFactory.getLog("jcl");
        log.info("jcl log");    // 不需要更改这种输出日志的语句
    }
}
