package org.zchzh.hellostartertest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.zchzh.hellostarter.YamlAndPropertySourceFactory;

/**
 * @author zengchzh
 * @date 2022/1/11
 */

@Component
@PropertySource(value = "classpath:test1.yml", factory = YamlAndPropertySourceFactory.class)
@ConfigurationProperties(prefix = "test")
public class TestYamlProp {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
