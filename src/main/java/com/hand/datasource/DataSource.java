package com.hand.datasource;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * 创建需要AOP的注解
 */
@Target({TYPE, METHOD})
@Retention(RUNTIME)
public @interface DataSource {
    String value();
}