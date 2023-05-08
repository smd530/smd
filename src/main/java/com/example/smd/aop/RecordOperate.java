package com.example.smd.aop;

import java.lang.annotation.*;

/**
 * @author shanmingda
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RecordOperate {

    String desc() default "";

    Class<? extends Convert> convert();
}
