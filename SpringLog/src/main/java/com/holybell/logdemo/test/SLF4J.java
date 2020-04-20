package com.holybell.logdemo.test;


//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 * 这个就跟JCL一样
 *
 * <dependency>
 *     <groupId>org.slf4j</groupId>
 *     <artifactId>slf4j-api</artifactId>
 *     <version>1.7.25</version>   // 注意注意上下版本号一致
 * </dependency>
 *
 * 只要引入了slf4j-api，那么就可以引入如下两种依赖，二者选其一就可以，slf4j-api提供了调用这些日志输出框架的逻辑
 *
 * 以下两种都是slf4j提供的日志框架绑定器，有的绑定器自身就包含了日志框架，而有的绑定器需要额外再引入绑定的日志框架依赖
 *
 * <dependency>
 *     <groupId>org.slf4j</groupId>
 *     <artifactId>slf4j-jdk14</artifactId>
 *     <version>1.7.25</version>   // 注意注意上下版本号一致
 * </dependency>
 * <dependency>
 *     <groupId>org.slf4j</groupId>
 *     <artifactId>slf4j-log4j12</artifactId>
 *     <version>1.7.25</version>  // 注意注意上下版本号一致   这里提供了log4j的实现，因此不需要额外引入log4j
 * </dependency>
 *
 */
public class SLF4J {

//    public static void main(String[] args) {
//        Logger logger = LoggerFactory.getLogger("slf4j");
//        logger.info("slf4j");
//    }
}
