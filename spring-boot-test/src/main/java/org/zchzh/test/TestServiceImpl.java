package org.zchzh.test;

import org.springframework.stereotype.Service;

/**
 * @author zengchzh
 * @date 2021/7/29
 */

@Service
public class TestServiceImpl implements TestService{


    @Override
    public String getMsg() {
        return "hello world";
    }

    @Override
    public String send(String msg) {
        return "get : "+ msg + ", hello world";
    }
}
