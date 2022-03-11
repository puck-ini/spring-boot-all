package org.zchzh.properties.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zengchzh
 * @date 2022/3/10
 */

@Component
@ConfigurationProperties(prefix = "info4")
public class InfoProperties4 {

    private String nameKey;

    private String nameType;


    public String getNameKey() {
        return nameKey;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    @Override
    public String toString() {
        return "InfoProperties4{" +
                "nameKey='" + nameKey + '\'' +
                ", nameType='" + nameType + '\'' +
                '}';
    }
}
