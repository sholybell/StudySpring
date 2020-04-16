package com.holybell.aop.test;

import com.holybell.aop.dao.Dao;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class OutputClassFile {
    /**
     * 生成一个接口的动态代理对象
     */
    public static void main(String[] args) throws IOException {
        Class<?>[] interfaces = new Class[]{Dao.class};
        byte bytes[] = ProxyGenerator.generateProxyClass("test", interfaces);
        File file = new File("d:\\test.class");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bytes);
        fos.flush();
        fos.close();
    }
}
