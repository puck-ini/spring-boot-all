package org.zchzh.hellostarter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zengchzh
 * @date 2021/9/29
 */

@RestController
@RequestMapping("/test")
public class HelloController {

    @Autowired
    private HelloService helloService;

//    @Autowired
//    private NameProp nameProp;

    @GetMapping("/msg")
    public String get() {
        return helloService.get();
    }
//
//    @GetMapping("/name")
//    public String getTest() {
//        return nameProp.getConfig();
//    }
}
