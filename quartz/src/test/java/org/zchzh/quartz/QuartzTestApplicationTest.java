package org.zchzh.quartz;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zchzh.quartz.job.HelloJob;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class QuartzTestApplicationTest {

    @Autowired
    private Scheduler scheduler;

    @Test
    void testJob() throws SchedulerException {
        scheduler.start();

        String jobId = HelloJob.class.getName();

        // 创建任务信息
        JobDetail jobDetail = JobBuilder
                // 任务类
                .newJob(HelloJob.class)
                // 添加该任务的唯一标识
                .withIdentity(jobId)
                .build();


        // 配置任务触发时间
        CronScheduleBuilder cronBuilder = CronScheduleBuilder.cronSchedule("0/1 * * * * ?");

        // 创建触发器
        CronTrigger cronTrigger =TriggerBuilder.newTrigger()
                // 添加唯一标识
                .withIdentity(jobId)
                // 添加任务触发实现
                .withSchedule(cronBuilder)
                .build();

        scheduler.scheduleJob(jobDetail, cronTrigger);
    }


    @Test
    public void addJob() throws SchedulerException {
        String jobId = HelloJob.class.getName();
        // 创建任务信息
        JobDetail jobDetail = JobBuilder
                // 任务类
                .newJob(HelloJob.class)
                // 添加该任务的唯一标识
                .withIdentity(jobId)
                .build();
        scheduler.addJob(jobDetail, true);
    }

    @AfterEach
    public void sleep() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}