package org.zchzh.test;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

/**
 * @author zengchzh
 * @date 2021/9/13
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpTest {

    /**
     * 获取当前服务的端口,需要配置 webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
     */
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void testApi() {
        String msg = restTemplate.getForObject("http://127.0.0.1:" + port + "/test/msg", String.class);
        Assert.assertEquals("hello world", msg);
        Assert.assertEquals("hello world1", msg);
    }
}
