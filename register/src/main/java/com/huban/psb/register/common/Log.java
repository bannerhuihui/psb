package com.huban.psb.register.common;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: code
 * @description:
 * @author: Dahui
 * @create: 2019-02-25 08:02
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

        String value() default "";

        boolean ignore() default false;

}
