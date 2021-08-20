package org.zchzh.websocket.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zengchzh
 * @date 2021/8/20
 */

@Slf4j
@ServerEndpoint("/ws/test")
@Component
public class WebsocketHandler {

    private static Map<String, Session> sessionHolder = new LinkedHashMap<>();

    private AtomicInteger count = new AtomicInteger(0);



    @OnOpen
    public void onOpen(Session session) {
        sessionHolder.put(session.getId(), session);
        log.info("new connect {}, current connect count {}", session.getId(), count.incrementAndGet());
        sendMessage(session.getId(), " connect success");
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        log.info("message : " + message);
        sendMessage(session.getId(), "message : " + message + " : " + new Date());
    }

    @OnError
    public void onError(Throwable throwable, Session session) {
        log.error("websocket error" + session.getId(), throwable);
    }

    @OnClose
    public void onClose(Session session) {
        sessionHolder.remove(session.getId());
        log.info("close connect {}, current connect count {}", session.getId(), count.decrementAndGet());
    }

    public static void sendMessage(String sessionId, String msg) {
        try {
            sessionHolder.get(sessionId).getBasicRemote().sendText(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void broadcast() {
        sessionHolder.forEach((k, v) -> {
            try {
                v.getBasicRemote().sendText("test");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
