package org.zchzh.nacosconsumer.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.zchzh.nacosapi.HelloService;

/**
 * @author zengchzh
 * @date 2021/4/16
 */

@RestController
public class HelloController {

    @Reference
    private HelloService helloService;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/get")
    public String get() {
        return helloService.get();
    }


    @GetMapping("/set")
    public String set(String msg) {
        return helloService.set(msg);
    }

    @GetMapping("/hello/{msg}")
    public String  helloMsg(@PathVariable String msg) {
        //获取服务发现系统中一个实例
        ServiceInstance serviceInstance = loadBalancerClient.choose("provider");
        String path = String.format("http://%s:%s/provider/%s", serviceInstance.getHost(),serviceInstance.getPort(),msg);
        String result = restTemplate.getForObject(path,String.class);
        return String.format("%s from %s %s", result, serviceInstance.getHost(),serviceInstance.getPort());
    }

}
