package org.zchzh.xxljob.job;

import com.xxl.job.core.handler.annotation.XxlJob;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author zengchzh
 * @date 2021/9/30
 */

@Slf4j
@Component
public class TestJob {


    @XxlJob("testJob")
    public void testJob() {
        log.info("test job start : " + LocalDateTime.now());
    }
}
