### AopDemo
展示切面的各种使用方式

### proxyCustom

模拟JDK接口代理的方式，生成代理对象

com.holybell.proxy.ProxyUtil : 假定增强逻辑不变，生成代理对象的工具类

com.holybell.proxy.ProxyUtil2 : 模拟JDK的Proxy类，使用接口和InvocationHanlder的方式，实现可以动态改变增强逻辑的代理类生成工具类

### MockSpring

com.holybell.org.spring.util.BeanFactory : 模拟Spring扫描解析XML文件，生成bean对象，模拟自动装配特性

com.holybell.org.spring.util.AnnotationConfigBeanFactory : 模拟Spring通过扫描注解，生成bean对象