package org.zchzh.eventlistener.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.zchzh.eventlistener.event.DemoEvent;

import java.util.Date;

/**
 * @author zengchzh
 * @date 2022/2/10
 */
@Slf4j
@Component
public class DemoListener1 {

    @Order
    @EventListener(DemoEvent.class)
    public void listener(DemoEvent demoEvent) {
        String msg = (String) demoEvent.getSource();
        log.info(Thread.currentThread().getName());
        log.info("listener msg - {} - time - {}", msg, new Date());
    }
}
