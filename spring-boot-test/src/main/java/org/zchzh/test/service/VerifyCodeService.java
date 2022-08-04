package org.zchzh.test.service;

/**
 * @author zengchzh
 * @date 2022/8/4
 */
public interface VerifyCodeService {

    String getCode();


    Boolean verify(String code);
}
