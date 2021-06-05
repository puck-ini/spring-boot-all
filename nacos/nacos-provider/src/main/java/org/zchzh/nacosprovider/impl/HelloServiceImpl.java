package org.zchzh.nacosprovider.impl;


import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;
import org.zchzh.nacosapi.HelloService;

/**
 * @author zengchzh
 * @date 2021/4/16
 */

@Service
public class HelloServiceImpl implements HelloService {

    @Value("${msg}")
    private String msg;


    @Override
    public String get() {
        return msg;
    }

    @Override
    public String set(String msg) {
        return get() + " : " + msg;
    }
}
