package org.zchzh.hellostartertest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zchzh.hellostarter.HelloService;

/**
 * @author zengchzh
 * @date 2021/1/27
 */

@SpringBootTest
public class TestStarter {

    @Autowired
    private HelloService helloService;

    @Test
    public void test(){
        System.out.println(helloService.get());
    }
}
