package org.zchzh.hellostarter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author zengchzh
 * @date 2022/1/11
 * starter 中不知道什么原因无法使用@PropertySource读取starter中的yml
 */

@Component
@PropertySource(value = "classpath:test.yml", factory = YamlAndPropertySourceFactory.class)
@ConfigurationProperties(prefix = "name")
public class NameProp {

    private String config;

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}
