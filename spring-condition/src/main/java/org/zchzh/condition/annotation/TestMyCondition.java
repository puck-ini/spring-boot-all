package org.zchzh.condition.annotation;

import org.springframework.context.annotation.Conditional;
import org.zchzh.condition.condition.TestCondition;

import java.lang.annotation.*;

/**
 * @author zengchzh
 * @date 2022/1/11
 */

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(TestCondition.class)
public @interface TestMyCondition {

    String value() default "";
}
