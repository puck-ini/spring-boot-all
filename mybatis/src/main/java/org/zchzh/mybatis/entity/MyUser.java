package org.zchzh.mybatis.entity;

import lombok.Data;

/**
 * @author zengchzh
 * @date 2023/8/9
 */

@Data
public class MyUser {

    private Long id;

    private String name;

    private String des;

    private String addr;

    private String msg;
}
