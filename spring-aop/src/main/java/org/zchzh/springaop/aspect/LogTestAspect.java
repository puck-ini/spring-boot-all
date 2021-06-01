package org.zchzh.springaop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author zengchzh
 * @date 2021/6/1
 */

@Slf4j
@Aspect
@Component
public class LogTestAspect {

    @Pointcut("@annotation(org.zchzh.springaop.annotation.LogTest)")
    public void logPointCut() {}

    @Around("logPointCut()")
    public Object log(ProceedingJoinPoint pjp) throws Throwable {
        log.info(">>>>>>>>>>aop log - {} ", new Date());
        return pjp.proceed();
    }
}
