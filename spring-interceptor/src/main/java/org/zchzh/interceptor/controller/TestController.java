package org.zchzh.interceptor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zengchzh
 * @date 2021/10/14
 */

@RestController
public class TestController {


    @GetMapping("/test")
    public String test() {
        if (true) {
            throw new RuntimeException("test");
        }
        return "hello world";
    }
}
