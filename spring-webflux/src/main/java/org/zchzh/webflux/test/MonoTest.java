package org.zchzh.webflux.test;

import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * @author zengchzh
 * @date 2021/9/14
 */
public class MonoTest {

    public static void main(String[] args) {
        Mono.just("are").subscribe(System.out::println);
        Mono.empty().subscribe(System.out::println);
        Mono.fromSupplier(() -> "you").subscribe(System.out::println);
        Mono.justOrEmpty(Optional.of("ok")).subscribe(System.out::println);
        Mono.create(sink -> sink.success("Hello")).subscribe(System.out::println);
    }
}
