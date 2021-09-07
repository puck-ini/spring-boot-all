package org.zchzh.cors.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zengchzh
 * @date 2021/8/31
 */

@RestController
public class TestController {


    /**
     * 方式三使用 @CrossOrigin 注解
     * @param msg
     * @return
     */
    @CrossOrigin(origins = "/*",
            methods = {RequestMethod.GET},
            maxAge = 3600,
            allowedHeaders = "*")
    @GetMapping("/test")
    public String test(String msg) {
        return msg;
    }
}
