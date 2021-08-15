package org.zchzh.provider;

import com.alibaba.dubbo.config.annotation.Service;
import org.zchzh.api.HelloService;

/**
 * @author zengchzh
 * @date 2021/8/15
 */

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello() {
        return "hello dubbo";
    }
}
