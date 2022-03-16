package org.zchzh.swagger.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author zengchzh
 * @date 2022/3/15
 */
@Slf4j
@ConditionalOnProperty(prefix = "swagger", name = "enable", havingValue = "false")
@RestController
public class DisableSwaggerUiController {

    /**
     * 通过这种方式只能访问不到 swagger ui 页面，api 接口文档地址还是能访问，需要在 swagger Docket 配置中配置 enable(false)
     * @param response
     */
//    @GetMapping(value = "/swagger-ui.html") 2.0版本
    @GetMapping(value = "/swagger-ui/index.html")
    public void getSwagger(HttpServletResponse response) {
        log.info("disable swagger");
        response.setStatus(HttpStatus.NOT_FOUND.value());
    }
}
