package org.zchzh.hellostarter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author zengchzh
 * @date 2021/1/27
 */

@Configuration
//@ConditionalOnClass(HelloService.class)
//@EnableConfigurationProperties(HelloProperties.class)
public class HelloAutoConfigure {

    @Bean
    public HelloProperties helloProperties() {
        return new HelloProperties();
    }
    @Bean
    @ConditionalOnMissingBean
    public HelloService helloService(@Autowired HelloProperties helloProperties) {
        return new HelloService(helloProperties.getMsg());
    }


    @Bean
    @Conditional(TestCondition.class)
    public HelloController helloController() {
        return new HelloController();
    }
}
