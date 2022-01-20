package org.zchzh.webclient;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author zchzh
 * @date 2022/1/20
 */

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebClientTests {

    @LocalServerPort
    private int port;

    private final String prefix = "http://127.0.0.1:";

    private String url;

    @BeforeEach
    void init() {
        url = prefix + port;
    }


    @Test
    void testMono() {
        WebClient webClient = WebClient.create(url);
        Mono<String> monoRes = webClient.method(HttpMethod.GET).uri("/mono").retrieve().bodyToMono(String.class);
        monoRes.subscribe(log::info, e -> {
            log.error("", e);
        });
    }


    @Test
    void testFlux() {
        WebClient webClient = WebClient.create(url);
        Flux<String> fluxRes = webClient.method(HttpMethod.GET).uri("/flux").retrieve().bodyToFlux(String.class);
        fluxRes.subscribe(log::info, e -> {
           log.error("", e);
        });
    }

    @AfterEach
    void sleep() throws InterruptedException {
        Thread.sleep(500);
    }



}
