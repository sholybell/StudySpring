package com.holybell.demo.import_demo.annotation;

import com.holybell.demo.import_demo.MyImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Import(MyImportSelector.class) // 导入MyImportSelector引入的类
public @interface EnableIndexDao {

}
