package org.zchzh.sentinel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zengchzh
 * @date 2021/8/20
 */

@RestController
public class TestController {

    @GetMapping("/get")
    public String getTest() {
        return "hello sentinel";
    }


    @PostMapping("/post")
    public String postTest(String msg) {
        return msg;
    }
}
