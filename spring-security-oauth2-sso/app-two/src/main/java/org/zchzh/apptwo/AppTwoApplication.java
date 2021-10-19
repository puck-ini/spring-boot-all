package org.zchzh.apptwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

/**
 * @author zengchzh
 * @date 2021/10/15
 */

@EnableOAuth2Sso
@SpringBootApplication
public class AppTwoApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppTwoApplication.class, args);
    }
}
