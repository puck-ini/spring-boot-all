package org.zchzh.properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zchzh.properties.config.*;

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

    @Autowired
    private InfoProperties4 infoProperties4;

    @Test
    public void test1() {
        System.out.println(infoProperties);
        System.out.println(userProperties);
        System.out.println(infoProperties1);
        System.out.println(infoProperties2);

        System.out.println(infoProperties4);
    }
}
