package org.zchzh.eventlistener.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

/**
 * @author zengchzh
 * @date 2021/7/1
 */

@Slf4j
@Component
public class StartListener1 {

    @EventListener(ContextRefreshedEvent.class)
    public void listenerEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (Objects.isNull(contextRefreshedEvent.getApplicationContext().getParent())) {
            log.info("StartListener1 - {}", new Date());
        }
    }
}
