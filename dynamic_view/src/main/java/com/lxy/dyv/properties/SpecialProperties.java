package com.lxy.dyv.properties;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author: lxy
 * created on: 2021/3/1 7:44 PM
 * <p>
 */
// maybe can revise to TextProperties & XXXProperties
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SpecialProperties {


    String key() default "";

    String method() default "";
}
