package com.example.demo1.config;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
@Documented
public @interface DynamicSource {

    String value() default "";
}
