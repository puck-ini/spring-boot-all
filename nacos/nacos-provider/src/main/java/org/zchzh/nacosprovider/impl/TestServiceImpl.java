package org.zchzh.nacosprovider.impl;

import org.apache.dubbo.config.annotation.Service;
import org.zchzh.nacosapi.TestService;

/**
 * @author zengchzh
 * @date 2021/4/16
 */

@Service
public class TestServiceImpl implements TestService {
    @Override
    public String test() {
        return "test";
    }
}
