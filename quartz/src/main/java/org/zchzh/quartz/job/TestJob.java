package org.zchzh.quartz.job;


import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.zchzh.quartz.job.base.BaseJob;

/**
 * @author zengchzh
 * @date 2021/3/4
 */
@Slf4j
public class TestJob implements BaseJob {
    @Override
    public void execute(JobExecutionContext context) {
        log.error("Test Job 执行时间: {}", DateUtil.now());
    }

}
