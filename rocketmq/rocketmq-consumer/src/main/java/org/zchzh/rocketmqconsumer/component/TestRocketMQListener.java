package org.zchzh.rocketmqconsumer.component;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author zengchzh
 * @date 2020/9/10 17:27
 */

@Slf4j
@Component
@RocketMQMessageListener(topic = "test2-topic",consumerGroup = "consumer-group")
public class TestRocketMQListener implements RocketMQListener<String> {
    @Override
    public void onMessage(String msg) {


        log.info("rocketmq message : " + msg);
    }
}
