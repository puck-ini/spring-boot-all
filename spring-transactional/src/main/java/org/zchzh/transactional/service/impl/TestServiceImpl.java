package org.zchzh.transactional.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zchzh.transactional.entity.TestEntity;
import org.zchzh.transactional.repository.TestRepo;
import org.zchzh.transactional.service.LogService;
import org.zchzh.transactional.service.TestService;

/**
 * @author zengchzh
 * @date 2021/8/23
 */

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestRepo testRepo;

    @Autowired
    private LogService logService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void test() {
        TestEntity testEntity = TestEntity.builder().msg("test1").build();
        testRepo.save(testEntity);
        logService.testException();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void testException() {
        TestEntity testEntity = TestEntity.builder().msg("test1").build();
        testRepo.save(testEntity);
        logService.test();
        throw new RuntimeException("test error");
    }
}
