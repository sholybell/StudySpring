package com.holybell.demo.import_demo.test;

import com.holybell.demo.import_demo.annotation.EnableIndexDao;
import org.springframework.context.annotation.ComponentScan;

//@Configuration
@EnableIndexDao
@ComponentScan("com.holybell.demo.import_demo.test")
public class Config {

}
