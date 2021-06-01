package org.zchzh.springaop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zchzh.springaop.annotation.LogTest;

import java.util.Random;

/**
 * @author zengchzh
 * @date 2021/6/1
 */

@Slf4j
@RestController
public class TestController {

    @LogTest
    @GetMapping("/test")
    public String test() {
        Random random = new Random();
        int i = random.nextInt(2);
        if (i == 1) {
            log.info("result i - {} ", i);
        } else {
            throw new RuntimeException("exception i - " + i);
        }
        return "hello world";
    }
}
