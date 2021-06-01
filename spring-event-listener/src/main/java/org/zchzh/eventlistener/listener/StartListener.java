package org.zchzh.eventlistener.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

/**
 * @author zengchzh
 * @date 2021/6/1
 */

@Slf4j
@Component
public class StartListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (Objects.isNull(contextRefreshedEvent.getApplicationContext().getParent())) {
            log.info("StartListener - {}", new Date());
        }
    }
}
