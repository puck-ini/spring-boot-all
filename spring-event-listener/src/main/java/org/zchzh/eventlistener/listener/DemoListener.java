package org.zchzh.eventlistener.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.zchzh.eventlistener.event.DemoEvent;

import java.util.Date;

/**
 * @author zengchzh
 * @date 2021/6/1
 */
@Slf4j
@Component
public class DemoListener {

    @Async
    @Order
    @EventListener(DemoEvent.class)
    public void listener(DemoEvent demoEvent) {
        String msg = (String) demoEvent.getSource();
        log.info("listener msg - {} - time - {}", msg, new Date());
    }
}
