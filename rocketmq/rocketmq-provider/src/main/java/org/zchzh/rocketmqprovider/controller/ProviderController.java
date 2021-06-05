package org.zchzh.rocketmqprovider.controller;

import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zengchzh
 * @date 2020/9/10 17:09
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/provider")
public class ProviderController {



    private final RocketMQTemplate rocketMQTemplate;


//    @Autowired
//    private Source source;


    @GetMapping("test1")
    public String getStr(){
        String topic = "test-topic";

        rocketMQTemplate.convertAndSend(topic,"provider");

        return "test1";
    }



    //分布式事务测试
    @GetMapping("/test2")
    public String test2(){

        String topic = "test2-topic";
        String msg = "test2";
        //rocketmq 4.4 需要添加txProducerGroup   4.7不需要
        rocketMQTemplate.sendMessageInTransaction(
//                "4.4",
                //消息topic
                topic,
                //消息体
                MessageBuilder

                        .withPayload(msg)
                        //设置消息体header，可设置多个
                        .setHeader(RocketMQHeaders.TRANSACTION_ID, "test2")
                        .build(),
                //arg
                msg
                );

        return "test2";
    }



//    spring cloud stream test
//    @GetMapping("/test3")
//    public String test3(){
//
//        source.output().send(MessageBuilder.withPayload("test3").build());
//        return "test3";
//    }


}
