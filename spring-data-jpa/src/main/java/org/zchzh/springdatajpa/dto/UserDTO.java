package org.zchzh.springdatajpa.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zengchzh
 * @date 2021/5/11
 */

@Data
public class UserDTO implements Serializable {

    private Long id;

    private String username;

    private String password;

    private String userType;

}
