package org.zchzh.eventlistener;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.zchzh.eventlistener.event.DemoEvent;

import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
class SpringEventListenerApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Test
    void contextLoads() {
        log.info(Thread.currentThread().getName());
        context.publishEvent(new DemoEvent("hello world"));
    }

}
