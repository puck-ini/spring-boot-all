package org.zchzh.resttemplate.config;

import lombok.extern.slf4j.Slf4j;
import org.omg.PortableInterceptor.ClientRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.*;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zengchzh
 * @date 2021/9/14
 */

@Configuration
public class RestTemplateConfig {

    @Bean("restTemplate")
    public RestTemplate defaultRestTemplate() {
        return new RestTemplate();
    }

    @Bean("simpleRestTemplate")
    public RestTemplate simpleRestTemplate(ClientHttpRequestFactory clientHttpRequestFactory) {
        RestTemplate restTemplate =  new RestTemplate(clientHttpRequestFactory);
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        MyInterceptor myInterceptor = new MyInterceptor();
        interceptors.add(myInterceptor);
        restTemplate.setInterceptors(interceptors);
        restTemplate.setErrorHandler(new MyHandler());
        return restTemplate;
    }

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000);
        factory.setConnectTimeout(15000);
        return factory;
    }


    /**
     * 自定义拦截器
     */
    @Slf4j
    static class MyInterceptor implements ClientHttpRequestInterceptor {

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            log.info("MyInterceptor ----------------" + LocalDateTime.now());
            return execution.execute(request, body);
        }
    }

    /**
     * 自定义 ErrorHandler。ErrorHandler 用来对调用错误对统一处理。
     */
    static class MyHandler extends DefaultResponseErrorHandler {

    }
}
