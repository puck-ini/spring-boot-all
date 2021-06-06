package org.zchzh.properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zchzh.properties.config.InfoProperties;
import org.zchzh.properties.config.UserProperties;

/**
 * @author zengchzh
 * @date 2021/6/6
 */

@SpringBootTest
public class PropertiesApplicationTest {

    @Autowired
    private InfoProperties infoProperties;

    @Autowired
    private UserProperties userProperties;

    @Test
    public void test1() {
        System.out.println(infoProperties);
        System.out.println(userProperties);
    }
}
