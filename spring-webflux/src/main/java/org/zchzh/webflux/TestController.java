package org.zchzh.webflux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author zengchzh
 * @date 2021/9/14
 */

@Slf4j
@RestController
public class TestController {


    @GetMapping("/sync")
    public String getSync() {
        log.info("start: " + LocalDateTime.now());
        String result = execute();
        log.info("end: " + LocalDateTime.now());
        return result;
    }


    @GetMapping("/async")
    public Mono<String> getAsync() {
        log.info("start: " + LocalDateTime.now());
        Mono<String> result = Mono.fromSupplier(this::execute);
        log.info("end: " + LocalDateTime.now());
        return result;
    }


    public String execute() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello world";
    }


    @GetMapping(value = "/async/flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> asyncFlux() {
        log.info("start: " + LocalDateTime.now());
        Flux<String> result = Flux.fromStream(IntStream.range(1, 5).mapToObj(i -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello" + i;
        }));
        log.info("end: " + LocalDateTime.now());
        return result;
    }
}
