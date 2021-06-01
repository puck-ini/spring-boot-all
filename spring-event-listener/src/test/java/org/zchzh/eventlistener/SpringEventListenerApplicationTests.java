package org.zchzh.eventlistener;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.zchzh.eventlistener.event.DemoEvent;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class SpringEventListenerApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Test
    void contextLoads() {
        context.publishEvent(new DemoEvent("hello world"));
    }

}
