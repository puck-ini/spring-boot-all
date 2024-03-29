package org.zchzh.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;


@MapperScan(basePackages = {"org.zchzh.quartz.mapper"})
@SpringBootApplication
public class QuartzTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuartzTestApplication.class, args);
    }

}
