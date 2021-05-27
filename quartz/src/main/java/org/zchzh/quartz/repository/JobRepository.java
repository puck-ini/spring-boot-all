package org.zchzh.quartz.repository;


import org.zchzh.quartz.entity.domain.JobAndTrigger;

import java.util.List;

/**
 * @author zengchzh
 * @date 2021/3/4
 */
public interface JobRepository {

    List<JobAndTrigger> list();

}
