package org.zchzh.redis.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author zengchzh
 * @date 2021/5/27
 */

@Data
@Builder
public class User {

    private Long id;

    private String username;

    private String password;

    private Integer age;

    private String name;
}
