package org.zchzh.nacosprovider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zengchzh
 * @date 2020/8/28 10:33
 */

@RestController
@RequestMapping("provider")
public class HelloController {

    @GetMapping("{msg}")
    public String getMsg(@PathVariable String msg){
        return String.format("hello %s",msg);
    }
}
