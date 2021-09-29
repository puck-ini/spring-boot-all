package org.zchzh.hellostartertest;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.zchzh.hellostarter.HelloService;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class HelloApplicationTest {

    @Autowired
    private HelloService helloService;

    @Value("${test.msg}")
    private String msg;

    
    @Test
    public void test() {
        Assert.assertEquals(helloService.get(), msg);
    }

}