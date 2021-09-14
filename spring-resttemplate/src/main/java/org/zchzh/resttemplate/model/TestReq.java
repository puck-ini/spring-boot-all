package org.zchzh.resttemplate.model;

import lombok.Data;

/**
 * @author zengchzh
 * @date 2021/9/14
 */

@Data
public class TestReq {

    private String msg;

    private String info;

    public TestReq() {
        this.msg = "test";
        this.info = "hello world";
    }

    public TestReq(String msg, String info) {
        this.msg = msg;
        this.msg = info;
    }

}
