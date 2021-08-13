package org.zchzh.springaop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author zengchzh
 * @date 2021/7/19
 */

@Aspect
@Component
public class ExecutionAspect {

    /**
     * 匹配所有方法
     */
    @Pointcut("execution(* *(..))")
    public void execution1() {}

    /**
     * 匹配 TestController 下的所有 public 方法
     */
    @Pointcut("execution(public * org.zchzh.springaop.controller.TestController.*(..))")
    public void execution2() {}

    /**
     * 匹配包下以及子包的所有方法
     */
    @Pointcut("execution(* org.zchzh.springaop..*.*(..))")
    public void execution3() {}

    /**
     * 支持 &&、||、! 运算符
     */
    @Pointcut("execution2() || execution3()")
    public void execution4() {}

    /**
     * 切面较多时可以将切面放在同一个类下，通过全类名以及方法名使用
     */
    @Pointcut("org.zchzh.springaop.aspect.ExecutionAspect.execution4()")
    public void execution5() {}

}
