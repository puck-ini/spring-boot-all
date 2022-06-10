package org.zchzh.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.servlet.http.HttpSessionListener;

/**
 * @author zengchzh
 * @date 2022/6/9
 */
@SpringBootApplication
public class SessionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SessionApplication.class, args);
    }

//
//    @Bean
//    public HttpSessionListener globalSessionListener() {
//        return new GlobalSessionListener();
//    }
}
