package org.zchzh.swagger;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
class SwaggerApplicationTest {



    RestTemplate restTemplate = new RestTemplate();


    @Test
    void getSwagger() {
        String json = restTemplate.getForObject("http://127.0.0.1:9990/api/swagger-ui/index.html", String.class);

        log.info(json);
    }

    @Test
    void getSwaggerV2ApiDoc() {
        String json = restTemplate.getForObject("http://127.0.0.1:9990/api/v2/api-docs", String.class);

        log.info(json);
    }

    @Test
    void getSwaggerV3ApiDoc() {
        String json = restTemplate.getForObject("http://127.0.0.1:9990/api/v3/api-docs", String.class);

        log.info(json);
    }


    @Test
    void test() {
        String s = "123.text";

        log.info(s.substring(s.indexOf(".")));
    }



}