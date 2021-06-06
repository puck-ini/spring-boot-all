package org.zchzh.properties.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * @author zengchzh
 * @date 2021/6/6
 */

@Component
//@ConditionalOnProperty(prefix = "info",name = "enable",havingValue = "true")
public class InfoProperties {

    @Value("${info.name}")
    private String name;

    @Value("${info.version}")
    private String version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "InfoProperties{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
