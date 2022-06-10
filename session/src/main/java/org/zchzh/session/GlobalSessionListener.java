package org.zchzh.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author zengchzh
 * @date 2022/6/9
 * @WebListener 不生效需要使用 @Bean 声明
 */
@Component
@Slf4j
//@WebListener
public class GlobalSessionListener implements HttpSessionListener {

    /**
     * session 被创建时执行
     * @param se
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.info("session create");
    }

    /**
     * 方法的执行：1、调用 session 的 invalidate() 方法 2、session 过期
     * @param se
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.info("session destroy");
    }
}
