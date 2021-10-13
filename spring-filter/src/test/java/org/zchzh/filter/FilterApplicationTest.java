package org.zchzh.filter;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FilterApplicationTest {

    @LocalServerPort
    private int port;

    private String http = "http://127.0.0.1:";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test() {
        String url = http + port;
        Assert.assertEquals(restTemplate.getForObject(url + "/test", String.class), "hello world");
    }
}