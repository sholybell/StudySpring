package com.holybell.demo.genmapper.config;

import com.holybell.demo.genmapper.annotation.MyScan;
import com.holybell.demo.genmapper.test.MyImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.holybell.demo.genmapper")
@MyScan
public class AppConfig {

}
