package org.zchzh.session;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * @author zengchzh
 * @date 2022/6/9
 *
 * HttpSession 的实现是 StandardSession 时该监听器可生效，在 spring boot 1.5版本中使用 spring-session-data-redis 时不生效
 */

@Slf4j
public class LocalSessionListener implements HttpSessionBindingListener {

    /**
     * 设置到 session 的 Attribute 时调用改方法
     * @param event
     */
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        log.info("绑定 session: " + event.getSession().getId());
    }

    /**
     * 方法的执行：1、调用 session 的 invalidate() 方法 2、session 过期 3、从 session 的 Attribute 被删除或者被覆盖
     * @param event
     */
    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        log.info("解除 session: " + event.getSession().getId());
    }
}
