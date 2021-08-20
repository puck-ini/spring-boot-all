package org.zchzh.websocket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zchzh.websocket.component.WebsocketHandler;

/**
 * @author zengchzh
 * @date 2021/8/20
 */

@RestController
public class WebsocketController {


    @GetMapping("/send")
    public void send(String sid, String msg) {
        WebsocketHandler.sendMessage(sid, msg);
    }

    @GetMapping("/broadcast")
    public void broadcast() {
        WebsocketHandler.broadcast();
    }
}
