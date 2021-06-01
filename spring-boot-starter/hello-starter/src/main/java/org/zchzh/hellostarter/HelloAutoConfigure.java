package org.zchzh.hellostarter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zengchzh
 * @date 2021/1/27
 */

@Configuration
@ConditionalOnClass(HelloService.class)
@EnableConfigurationProperties(HelloProperties.class)
public class HelloAutoConfigure {

    private final HelloProperties helloProperties;

    public HelloAutoConfigure(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }


    @Bean
    @ConditionalOnMissingBean
    public HelloService helloService () {
        return new HelloService(helloProperties.getMsg());
    }
}
