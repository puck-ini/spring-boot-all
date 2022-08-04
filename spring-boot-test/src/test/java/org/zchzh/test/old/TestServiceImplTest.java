package org.zchzh.test.old;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.zchzh.test.service.old.TestServiceImpl;

/**
 * TestMe 插件自动生成
 */
class TestServiceImplTest {
    TestServiceImpl testServiceImpl = new TestServiceImpl();

    @Test
    void testGetMsg() {
        String result = testServiceImpl.getMsg();
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testSend() {
        String result = testServiceImpl.send("msg");
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme