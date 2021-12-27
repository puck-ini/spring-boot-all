package org.zchzh.springload.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author zengchzh
 * @date 2021/12/27
 */

@Slf4j
@Component
public class AfterBeanInitConfig2 implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info(AfterBeanInitConfig2.class.getSimpleName() + " : " + LocalDateTime.now());
    }
}
