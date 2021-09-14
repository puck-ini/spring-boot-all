package org.zchzh.resttemplate.model;

import lombok.Data;

/**
 * @author zengchzh
 * @date 2021/9/14
 */

@Data
public class Result {

    String code;

    String msg;

    String data;

    public Result() {
        this.code = "00000";
        this.msg = "success";
        this.data = "hello world";
    }

    public Result(String data) {
        this.code = "00000";
        this.msg = "success";
        this.data = data;
    }

    public Result(String code, String msg, String data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
