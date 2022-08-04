package org.zchzh.test.old;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.zchzh.test.controller.TestController;
import org.zchzh.test.service.old.TestService;
import org.zchzh.test.service.old.TestService1;
import org.zchzh.test.service.old.TestServiceImpl;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class TestControllerTest {


    @InjectMocks
    private TestController testController;

    @InjectMocks
    private TestServiceImpl testServiceImpl;

    @Mock
    private TestService testService;

    @Mock
    private TestService1 testService1;

    @BeforeEach
    void init() {
        // 初始化mock
        MockitoAnnotations.initMocks(this);

        // 为接口方法添加假数据，如果测试的对象有依赖以下接口时，在测试对象调用该方法时会执行以下的配置
        Mockito.when(testService.getMsg()).thenReturn("hello world");
//        Mockito.when(testService.send("msg")).thenReturn("msg");
        // 类似于实现该接口
        Mockito.when(testService.send(Mockito.any())).thenAnswer((Answer<String>) invocationOnMock -> {
            log.info("testService send()");
            String msg = invocationOnMock.getArgument(0);
            if (Objects.isNull(msg)) {
                throw new IllegalArgumentException("msg 不能为 null");
            }
            return msg;
        });

        Mockito.when(testService1.get1()).thenReturn("test");
        Mockito.when(testService1.get2()).thenReturn("test");
    }


    @Test
    void testGetIfResultIsOk() {
        String expectStr = "hello world";
        assertEquals(expectStr, testController.get());
    }


    @Test
    void testSendIfResultIsOk() {
        String expectStr = "msg";
        assertEquals(expectStr, testController.send(expectStr));
    }

    @Test
    void testSendIfMsgIsNull() {
        assertThrows(IllegalArgumentException.class, () -> testController.send(null));
    }

    @Test
    void testTestService1() {
        String expect = "test";
        assertEquals(expect, testService1.get1());
        assertEquals(expect, testService1.get2());
    }

}