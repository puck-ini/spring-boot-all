package org.zchzh.rocketmqprovider.component;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

/**
 * @author zengchzh
 * @date 2020/9/10 20:06
 * 处理 msg -> A -> msg -> mq (use msg do A) -> msg -> B (use msg do B)
 *
 * 如何处理 msg -> A (use msg do A) -> msg2 -> mq -> msg2 -> B (use msg2 do B) ?
 */

@Slf4j
@Component
//rocketmq 4.4 需要添加txProducerGroup   4.7不需要
@RocketMQTransactionListener
public class ProviderRocketMQTransactionListener implements RocketMQLocalTransactionListener {

    /**
     * 执行本地事务
     * @param message 消息体
     * @param o arg
     * @return
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {

        MessageHeaders messageHeaders = message.getHeaders();
        String transactionId = (String) messageHeaders.get(RocketMQHeaders.TRANSACTION_ID);

        //执行事务，记录消息状态日志  例如 数据库操作
        try {
            log.info("executeLocalTransaction: " + transactionId);
            //provider操作
            log.info("insert xxx");
            //记录日志 transactionId可作为主键
            log.info("insert log");


            //成功返回commit
            return RocketMQLocalTransactionState.COMMIT;
        }catch (Exception e){
            log.error(e.getMessage());
            //失败返回rollback
            return RocketMQLocalTransactionState.ROLLBACK;
        }

    }

    /**
     * 本地事务检车
     * @param message
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        //从日志中查找消息状态
        log.info("select log");
        //if log != null return commit else return rollback
        if (true){
            return RocketMQLocalTransactionState.COMMIT;
        }else {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }
}
