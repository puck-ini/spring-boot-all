package org.zchzh.websocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author zengchzh
 * @date 2022/12/1
 */
public class WebSocketClientTest {

    public static void main(String[] args) throws InterruptedException {
        List<WebSocketClient> clientList = new ArrayList<>();
        try {
            final URI uri = new URI("http://localhost:8080/ws/test");
            IntStream.range(0, 2).forEach(item -> {
                WebSocketClient msgWebSocketClient = new MsgWebSocketClient(uri);
                clientList.add(msgWebSocketClient);
                try {
                    msgWebSocketClient.connectBlocking();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 10; i++) {
                    msgWebSocketClient.send("msg-" + i);
                }

            });
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        TimeUnit.SECONDS.sleep(1);
        for (WebSocketClient webSocketClient : clientList) {
            try {
                webSocketClient.closeBlocking();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    static class MsgWebSocketClient extends WebSocketClient {

        public MsgWebSocketClient(URI serverUri) {
            super(serverUri);
        }

        @Override
        public void onOpen(ServerHandshake handshakedata) {
            System.out.println("handshakedata = " + handshakedata);
        }

        @Override
        public void onMessage(String message) {
            System.out.println("message = " + message);
        }

        @Override
        public void onClose(int code, String reason, boolean remote) {
            System.out.println("code = " + code + ", reason = " + reason + ", remote = " + remote);
        }

        @Override
        public void onError(Exception ex) {
            System.out.println("ex = " + ex);
        }
    }
}
