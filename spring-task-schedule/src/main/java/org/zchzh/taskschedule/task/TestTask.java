package org.zchzh.taskschedule.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;
import java.util.Random;

/**
 * @author zengchzh
 * @date 2021/8/13
 */

@Slf4j
@Component
public class TestTask {
    Random random = new Random();


    /**
     * 间隔3s运行，fixedDelay表示上次任务结束后到下次任务开始之间的间隔为3s
     */
    @Scheduled(fixedDelay = 3000)
    public void task1() {
        log.info("task1 : " + new Date());
        if (Objects.equals(random.nextInt(3), 1)) {
            throw new IllegalStateException();
        }
    }

    /**
     * 间隔3s运行，fixedRate表示上次任务开始时到下次任务开始之间的间隔为3s
     */
    @Scheduled(fixedRate = 3000)
    public void task2() {
        log.info("task2 : " + new Date());
    }

    /**
     * 表示初始化1s后执行，之后间隔3s执行
     */
    @Scheduled(initialDelay = 1000, fixedRate = 3000)
    public void task3() {
        log.info("task3 : " + new Date());
    }

    /**
     * cron表达式，每秒执行一次
     */
    @Scheduled(cron = "0/1 * * * * ?")
    public void task4() {
        log.info("task4 : " + new Date());
    }
}
