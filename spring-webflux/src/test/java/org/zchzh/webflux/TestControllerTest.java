package org.zchzh.webflux;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String URL = "http://127.0.0.1:";
    @Test
    void getSync() {
        String result = restTemplate.getForObject(URL + port + "/sync", String.class);
        log.info("test: " + result + " time : " + LocalDateTime.now());
    }

    @Test
    void getAsync() {
        String result = restTemplate.getForObject(URL + port + "/async", String.class);
        log.info("test: " + result + " time : " + LocalDateTime.now());
    }


    @Test
    void asyncFlux() {
        String result = restTemplate.getForObject(URL + port + "/async/flux", String.class);
        log.info("test: " + result + " time : " + LocalDateTime.now());
    }
}