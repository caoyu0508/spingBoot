package com.sfac.javaSpringBoot.aspect;

import java.lang.annotation.*;
//指定方法级别还是类级别的
@Target(ElementType.METHOD)
//说明是一个文档
@Documented
//指定运行的环境
@Retention(RetentionPolicy.RUNTIME)

//自定义
public @interface ServiceAnnotation {
    String value() default "aaa";
}
