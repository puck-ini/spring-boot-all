package org.zchzh.transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zchzh.transactional.service.LogService;
import org.zchzh.transactional.service.TestService;

/**
 * @author zengchzh
 * @date 2021/8/23
 */
@SpringBootTest(classes = TransactionalApplication.class)
public class TransactionalApplicationTests {

    @Autowired
    private TestService testService;
//
//    @Autowired
//    private LogService logService;

    @Test
    public void test() {
        testService.test();
    }

    @Test
    public void test1() {
        testService.testException();
    }
}
