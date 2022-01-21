package org.zchzh.test;

import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author zengchzh
 * @date 2021/7/29
 */

@Service
public class TestServiceImpl implements TestService {


    @Override
    public String getMsg() {
        return "hello world";
    }

    @Override
    public String send(String msg) {
        if (Objects.isNull(msg)) {
            throw new IllegalArgumentException("msg 不能为null");
        }
        return msg;
    }
}
