package org.zchzh.rocketmqconsumer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zengchzh
 * @date 2020/9/10 17:08
 */

@Slf4j
@RestController
@RequestMapping("/provider")
public class ConsumerController {


    @GetMapping("test1")
    public String getStr(){
        return "test1";
    }

}
