package com.holybell.demo.configuration.config;

import com.holybell.demo.configuration.bean.MyBean1;
import com.holybell.demo.configuration.bean.MyBean2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Spring启动的时候，在扫描类的时候
 * 使用了@Configuration注解的类会被定义为一个full配置类
 * 而使用了@Component、@ComponentScan、@Import、@ImportSource这4个注解会被定义为lite配置类
 *
 * 这二者的区别是，
 * full配置类Spring会对其使用CGLIB动态代理生成对象注入Spring容器
 * 而lite就是类本身的对象
 *
 * 那么为什么要进行动态代理？
 *
 * 参考本例，myBean2()中调用了myBean1()，那么Spring在解析Bean配置的时候，是否会执行两次myBean1()，
 * 从而创建两个MyBean1对象？
 *
 * 使用了@Configuration注解的配置类，CGLIB之后，会是一个实现了BeanFactoryAware子接口的类，
 * 那么Spring在实例化这个代理类的时候，就会注入Spirng的BeanFactory容器，当Spirng解析到要调用的myBean1()
 * 是一个使用了@Bean标注的方法，就会直接从BeanFactory去获取，获取不到才会调用创建。
 *
 * 原理是CGLIB生成的代理对象在执行@Bean方法的时候，会判断目前调用的方法是否和自己生成的代理方法同名，
 * 不同名就尝试去BeanFactory先获取对象。比如执行MyBean2()对应的代理类中MyBean2()，
 * 当他调用MyBean1()的时候，代理对象执行的方法就和调用的方法不同名了。
 *
 * 但是如果@Bean标注的方法使用了static关键字，@Configuration标注的配置类还是会执行多次
 */
//@Configuration
//@ComponentScan("com.holybell.demo.configuration")
@Component
public class Config {

    @Bean
    public MyBean1 myBean1() {
        return new MyBean1();
    }

    @Bean
    public MyBean2 myBean2() {
        myBean1();
        return new MyBean2();
    }
}
