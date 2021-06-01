package org.zchzh.springaop.annotation;

import java.lang.annotation.*;

/**
 * @author zengchzh
 * @date 2021/6/1
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface LogTest {
}
