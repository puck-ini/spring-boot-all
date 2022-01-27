package org.zchzh.properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zchzh.properties.config.InfoProperties;
import org.zchzh.properties.config.InfoProperties1;
import org.zchzh.properties.config.InfoProperties2;
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

    @Autowired
    private InfoProperties1 infoProperties1;

    @Autowired
    private InfoProperties2 infoProperties2;

    @Test
    public void test1() {
        System.out.println(infoProperties);
        System.out.println(userProperties);
        System.out.println(infoProperties1);
        System.out.println(infoProperties2);
    }
}
