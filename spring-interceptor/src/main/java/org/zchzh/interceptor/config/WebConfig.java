package org.zchzh.interceptor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.zchzh.interceptor.interceptor.LogInterceptor;

/**
 * @author zengchzh
 * @date 2021/10/14
 *
 *
 * 两种配置方式，使用其中一种即可
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor());
    }


//    @Bean
//    public WebMvcConfigurer createWebMvcConfig(@Autowired HandlerInterceptor[] interceptors) {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addInterceptors(InterceptorRegistry registry) {
//                for (HandlerInterceptor interceptor : interceptors) {
//                    registry.addInterceptor(interceptor);
//                }
//            }
//        };
//    }
}
