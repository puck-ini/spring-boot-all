package org.zchzh.taskschedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author zengchzh
 * @date 2021/8/13
 */

@EnableScheduling
@SpringBootApplication
public class TaskScheduleApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskScheduleApplication.class);
    }
}
