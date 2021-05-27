package org.zchzh.hellostarter;

/**
 * @author zengchzh
 * @date 2021/1/27
 */
public class HelloService {

    private String msg;

    public HelloService(String msg) {
        this.msg = msg;
    }

    public String get(){
        return msg;
    }
}
