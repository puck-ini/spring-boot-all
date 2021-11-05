package org.zchzh.provider;

import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;
import org.zchzh.api.HelloService;

/**
 * @author zengchzh
 * @date 2021/8/15
 */

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello() {
        String test = RpcContext.getContext().getAttachment("test");
        return "hello dubbo";
    }
}
