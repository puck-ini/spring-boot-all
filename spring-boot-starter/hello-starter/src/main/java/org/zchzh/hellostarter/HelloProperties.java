package org.zchzh.hellostarter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zengchzh
 * @date 2021/1/27
 */

@ConfigurationProperties("test")
public class HelloProperties {

    private String msg = "msg";

    private TestEnum aenum;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public TestEnum getAenum() {
        return aenum;
    }

    public void setAenum(TestEnum aenum) {
        this.aenum = aenum;
    }
}
