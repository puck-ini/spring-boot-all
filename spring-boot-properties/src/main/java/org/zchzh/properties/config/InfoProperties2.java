package org.zchzh.properties.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author zengchzh
 * @date 2022/1/12
 */
@Component
@PropertySource(value = "classpath:info.yml", factory = YamlAndPropertySourceFactory.class)
@ConfigurationProperties(prefix = "info2")
public class InfoProperties2 {

    private String name;

    private String version;

    private Boolean enable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "InfoProperties2{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", enable=" + enable +
                '}';
    }
}
