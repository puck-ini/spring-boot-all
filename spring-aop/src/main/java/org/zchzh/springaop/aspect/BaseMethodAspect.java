package org.zchzh.springaop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author zengchzh
 * @date 2021/9/10
 */
public abstract class BaseMethodAspect implements ApplicationContextAware {

    protected abstract void pointcut();

    @Around("pointcut()")
    public Object advice(ProceedingJoinPoint point) {
        Class<? extends MethodAdviceHandler<?>> handlerType =  getAdviceHandlerType();
        MethodAdviceHandler<?> adviceHandler = appContext.getBean(handlerType);
        return advice(point, adviceHandler);
    }

    /**
     * 获得切面绑定的方法增强处理器的类型
     */
    protected abstract Class<? extends MethodAdviceHandler<?>> getAdviceHandlerType();

    /**
     * 使用方法增强处理器增强被注解的方法
     *
     * @param point   连接点
     * @param handler 切面处理器
     * @return 方法执行返回值
     */
    private Object advice(ProceedingJoinPoint point, MethodAdviceHandler<?> handler) {
        // 执行之前，返回是否被允许执行
        boolean permitted = handler.onBefore(point);

        // 方法返回值
        Object result;
        // 是否抛出了异常
        boolean thrown = false;
        // 开始执行的时间
        long startTime = System.currentTimeMillis();

        RuntimeException ce = new RuntimeException();
        // 目标方法被允许执行
        if (permitted) {
            try {
                // 执行目标方法
                result = point.proceed();
            } catch (Throwable e) {
                // 抛出异常
                thrown = true;
                // 处理异常
                handler.onThrow(point, e);
                // 抛出异常时的返回值
                result = handler.getOnThrow(point, e);
                ce = new RuntimeException(e.getMessage());
            }
        } else { // 目标方法被禁止执行
            // 禁止执行时的返回值
            result = handler.getOnForbid(point);
        }

        // 结束
        handler.onComplete(point, startTime, permitted, result);
        if(thrown) {
            throw ce;
        }
        return result;
    }

    private ApplicationContext appContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }
}
