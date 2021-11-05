package org.zchzh.consumer;

import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zchzh.api.HelloService;

/**
 * @author zengchzh
 * @date 2021/8/15
 */

@RestController
public class HelloController {

    @Reference
    private HelloService helloService;

    @GetMapping("/hello")
    public String hello() {
        RpcContext.getContext().setAttachment("test", "hello world");
        String result  = helloService.hello();
        String test = RpcContext.getContext().getAttachment("test");
        return result;
    }
}
