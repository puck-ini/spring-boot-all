package org.zchzh.appone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

/**
 * @author zengchzh
 * @date 2021/10/15
 */

@EnableOAuth2Sso
@SpringBootApplication
public class AppOneApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppOneApplication.class, args);
    }
}
