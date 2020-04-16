package com.holybell.org.spring.util;

import com.holybell.annotation.RegisterBean;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AnnotationConfigBeanFactory {

    private Map<String, Object> beanMap = new ConcurrentHashMap<>();

    public void scan(String basePackage) {
        //获取生产环境的类目录
        String rootPath = this.getClass().getResource("/").getPath();
        String basePackagePath = basePackage.replaceAll("\\.", "\\\\");
        File file = new File(rootPath + "//" + basePackagePath);
        // 获取.class文件名称数组
        String[] names = file.list();
        for (String name : names) {
            // 暴力删除.class后缀
            name = name.replace(".class", "");
            try {
                // 通过这个也说明，不要随便给Spring一个扫描的目录，因为Spring需要将所有的class文件加载到JVM
                // 才能判断这个class是否需要被自己管理，太大的目录会造成无谓的扫描开销
                Class clazz = Class.forName(basePackage + "." + name);
                // 判断是否属于@Service等需要管理的类
                if (clazz.isAnnotationPresent(RegisterBean.class)) {
                    RegisterBean registerBean = (RegisterBean) clazz.getAnnotation(RegisterBean.class);
                    beanMap.put(registerBean.value(), clazz.newInstance());
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    public Object getObject(String beanName) {
        return beanMap.get(beanName);
    }
}
