package org.zchzh.transactional.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zchzh.transactional.entity.LogEntity;
import org.zchzh.transactional.repository.LogRepo;
import org.zchzh.transactional.service.LogService;
import org.zchzh.transactional.service.TestService;

/**
 * @author zengchzh
 * @date 2021/8/23
 */

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepo logRepo;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void test() {
        LogEntity logEntity = LogEntity.builder().msg("log").build();
        logRepo.save(logEntity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void testException() {
        LogEntity logEntity = LogEntity.builder().msg("log").build();
        logRepo.save(logEntity);
        throw new RuntimeException("log error");
    }
}
