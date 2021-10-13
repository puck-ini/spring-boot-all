package org.zchzh.filter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zengchzh
 * @date 2021/10/13
 */

@RestController
public class TestController {



    @GetMapping("/test")
    public String test() {
        return "hello world";
    }
}
