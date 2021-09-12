package org.zchzh.springaop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zengchzh
 * @date 2021/9/10
 *
 * 用于产生调用记录的注解，会记录下方法的出入参、调用时长
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface InvokeRecord {

    /**
     * 调用说明
     */
    String value() default "";
}
