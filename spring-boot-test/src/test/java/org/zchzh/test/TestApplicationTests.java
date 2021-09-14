package org.zchzh.test;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.Rollback;

import java.util.concurrent.TimeUnit;

/**
 * @author zengchzh
 * @date 2021/7/29
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestApplicationTests {

    /**
     * 获取当前服务的端口,需要配置 webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
     */
    @LocalServerPort
    private int port;

    @Autowired
    private TestService testService;


    @Test
    @DisplayName("测试TestService getMsg()")
    public void testGetMsg() {
        System.out.println(testService.getMsg());
    }

    @Test
    @DisplayName("测试TestService send()")
    public void testSend() {
        System.out.println(testService.send("hello world"));
    }

    @Test
    @DisplayName("测试http getMsg()")
    public void testHttpGetMsg() {

    }

    @Test
    @DisplayName("测试http send()")
    public void testHttpSend() {

    }

    @Test
    @DisplayName("测试别名")
    void testDisplayName() {
        System.out.println("测试别名");
    }


    @Test
    @BeforeAll
    static void testBefore() {
        System.out.println("所有测试方法执行前执行");
    }

    @Test
    @BeforeEach
    void testBeforeEach() {
        System.out.println("每个测试方法执行前执行");
    }

    @Test
    @AfterAll
    static void testAfter() {
        System.out.println("所有测试方法执行后执行");
    }

    @Test
    @AfterEach
    void testAfterEach() {
        System.out.println("每个测试方法执行后执行");
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.MILLISECONDS)
    void testTimeOut() throws InterruptedException {
        System.out.println("测试方法超时时间，超过指定时间会报错");
        TimeUnit.SECONDS.sleep(1);
    }

    @Test
    @Disabled
    void testDisable() {
        System.out.println("测试方法不执行");
    }

    @RepeatedTest(value = 3)
    void testRepeatedTest() {
        System.out.println("测试方法重复执行");
    }

    /**
     * 测试执行结束回滚操作，主要用于对数据库操作
     */
    @Test
    @Rollback
    void testRollBack() {

    }


}
