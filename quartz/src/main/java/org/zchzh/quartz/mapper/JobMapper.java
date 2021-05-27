package org.zchzh.quartz.mapper;


import org.springframework.stereotype.Component;
import org.zchzh.quartz.entity.domain.JobAndTrigger;

import java.util.List;

/**
 * @author zengchzh
 * @date 2021/3/5
 */

@Component
public interface JobMapper {

    /**
     * 查询定时作业和触发器列表
     *
     * @return 定时作业和触发器列表
     */
    List<JobAndTrigger> list();
}
