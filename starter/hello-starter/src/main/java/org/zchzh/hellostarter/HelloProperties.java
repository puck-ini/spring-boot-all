package org.zchzh.hellostarter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zengchzh
 * @date 2021/1/27
 */

@ConfigurationProperties("test")
public class HelloProperties {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
