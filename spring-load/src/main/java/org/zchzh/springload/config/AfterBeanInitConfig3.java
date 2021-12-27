package org.zchzh.springload.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author zengchzh
 * @date 2021/12/27
 */

@Slf4j
@Component
public class AfterBeanInitConfig3 implements SmartLifecycle {

    private boolean running = false;

    /**
     * 所有对象已被实例化和初始化之后根据 isAutoStartup() 返回的值是否运行该方法
     */
    @Override
    public void start() {
        log.info(AfterBeanInitConfig3.class.getSimpleName() + " : " + LocalDateTime.now());
        running = true;
    }

    @Override
    public void stop(Runnable callback) {
        stop();
        callback.run();
        log.info(AfterBeanInitConfig3.class.getSimpleName() + " stop callback : " + LocalDateTime.now());
    }

    @Override
    public void stop() {
        log.info(AfterBeanInitConfig3.class.getSimpleName() + " stop : " + LocalDateTime.now());
        running = false;
    }

    /**
     *
     * @return isRunning() 返回 false 时 start() 才会执行， 返回 true 时 stop() 和 stop(callback) 才会被执行
     */
    @Override
    public boolean isRunning() {
        return running;
    }

    /**
     * isAutoStartup() 表示 start() 是否要在 bean 初始化完成后自动执行
     * @return 默认为 true，true 表示运行 start()，false 表示不运行
     */
    @Override
    public boolean isAutoStartup() {
        return true;
    }

    /**
     * 有多个类实现 SmartLifecycle 时，通过该方法判断执行顺序
     * @return 返回实现类的执行顺序，返回的值越小优先级越高
     */
    @Override
    public int getPhase() {
        return 0;
    }
}
