package org.zchzh.springload.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author zengchzh
 * @date 2021/12/27
 */
@Slf4j
@Component
public class AfterBeanInitConfig1 implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info(AfterBeanInitConfig1.class.getSimpleName() + " : " + LocalDateTime.now());
    }
}
