package org.zchzh.rocketmqconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableBinding(Sink.class)
@SpringBootApplication
public class RocketMqConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketMqConsumerApplication.class, args);
    }

}
