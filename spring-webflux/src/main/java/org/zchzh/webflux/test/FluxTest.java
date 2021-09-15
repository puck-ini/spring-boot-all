package org.zchzh.webflux.test;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author zengchzh
 * @date 2021/9/14
 */
public class FluxTest {

    public static void main(String[] args) {
        System.out.println("just");
        Flux.just("Hello", "World").subscribe(System.out::println);
        System.out.println("fromArray");
        Flux.fromArray(new Integer[] {1, 2, 3}).subscribe(System.out::println);
        System.out.println("empty");
        Flux.empty().subscribe(System.out::println);
        System.out.println("range");
        Flux.range(1, 4).subscribe(System.out::println);
        System.out.println("interval");
        Flux.interval(Duration.of(1, ChronoUnit.SECONDS)).subscribe(System.out::println);

        System.out.println("generate1");
        Flux.generate(sink -> {
            sink.next("Hello");
            sink.complete();
        }).subscribe(System.out::println);

        System.out.println("generate2");
        final Random random = new Random();
        Flux.generate(ArrayList::new, (list, sink) -> {
            int value = random.nextInt(100);
            list.add(value);
            sink.next(value);
            if (list.size() == 10) {
                sink.complete();
            }
            return list;
        }).subscribe(System.out::println);

        System.out.println("create");
        Flux.create(sink -> {
            for (int i = 0; i < 10; i++) {
                sink.next(i);
            }
            sink.complete();
        }).subscribe(System.out::println);
        // 线程延迟关闭
        try {
            Thread.currentThread().join(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
