package org.zchzh.taskschedule.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.time.LocalDateTime;

/**
 * @author zengchzh
 * @date 2021/11/8
 */

@Slf4j
@Configuration
public class TaskConfig implements SchedulingConfigurer {

    @Value("${task.cron:0/1 * * * * ?}")
    private String cron;


    /**
     * SchedulingConfigurer 默认使用单线程模式，使用多线程需要配置
     * @param taskRegistrar
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        // 设置多线程执行
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        taskScheduler.initialize();
        taskRegistrar.setTaskScheduler(taskScheduler);

        taskRegistrar.addCronTask(() -> {
            log.info(Thread.currentThread().getName() + " : config task1 : " + LocalDateTime.now());
        }, cron);

        taskRegistrar.addFixedDelayTask(() -> {
            log.info(Thread.currentThread().getName() + " : config task2 : " + LocalDateTime.now());
        }, 1000);

        taskRegistrar.addTriggerTask(() -> {
            log.info(Thread.currentThread().getName() + " : config task3 : " + LocalDateTime.now());
        }, triggerContext -> {
            return new CronTrigger(cron).nextExecutionTime(triggerContext);
        });


    }
}
