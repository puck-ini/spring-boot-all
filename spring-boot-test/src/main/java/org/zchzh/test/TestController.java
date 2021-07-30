package org.zchzh.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zengchzh
 * @date 2021/7/29
 */

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService  testService;

    @GetMapping("/msg")
    public String get() {
        return testService.getMsg();
    }

    @PostMapping("/send")
    public String send(@RequestBody String msg) {
        return testService.send(msg);
    }
}
