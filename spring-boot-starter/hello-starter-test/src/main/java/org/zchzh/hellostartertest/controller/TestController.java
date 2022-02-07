package org.zchzh.hellostartertest.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zchzh.hellostarter.HelloService;
import org.zchzh.hellostartertest.config.TestYamlProp;

/**
 * @author zengchzh
 * @date 2021/1/27
 */

@RestController
public class TestController {

    @Autowired
    private HelloService helloService;

    @Autowired
    private TestYamlProp testYamlProp;

    @GetMapping("/test")
    public String test(){
        return helloService.get();
    }

    @GetMapping("/testyaml")
    public String testYaml() {
        return testYamlProp.getName();
    }
}
