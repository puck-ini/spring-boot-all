package org.zchzh.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zchzh.test.service.old.TestService;

/**
 * @author zengchzh
 * @date 2021/7/29
 */

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/msg")
    public String get() {
        return testService.getMsg();
    }

    @PostMapping("/send")
    public String send(@RequestBody String msg) {
        return testService.send(msg);
    }
}
