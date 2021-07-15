package org.zchzh.springdatajpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author zengchzh
 * @date 2021/5/11
 */

@Data
@ToString
@NoArgsConstructor
public class UserDTO implements Serializable {

    private Long id;

    private String username;

    private String password;

    private String userType;

    private String name;

    private Integer age;

    public UserDTO(Long id, String username, String password, String name, Integer age) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
    }

    public UserDTO(Long id, String username, String password, String userType, String name, Integer age) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.name = name;
        this.age = age;
    }
}
