package org.zchzh.eventlistener.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

import java.util.Date;

/**
 * @author zengchzh
 * @date 2021/6/1
 */
@Slf4j
public class DemoEvent extends ApplicationEvent {
    public DemoEvent(String msg) {
        super(msg);
        log.info("event msg - {} - time - {}", msg, new Date());
    }
}
