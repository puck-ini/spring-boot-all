package org.zchzh.webclient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author zchzh
 * @date 2022/1/20
 */

@RestController
public class TestController {


    @GetMapping("/mono")
    public Mono<String> mono() {
        return Mono.just("hello world");
    }


    @GetMapping("/flux")
    public Flux<String> flux() {
        return Flux.fromStream(IntStream.range(0, 10).mapToObj(i -> {
            try {
                TimeUnit.MICROSECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello " + i;
        }));
    }
}
