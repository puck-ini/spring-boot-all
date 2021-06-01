package org.zchzh.springdatajpa.types;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Embeddable;

/**
 * @author zengchzh
 * @date 2021/5/4
 */

@Embeddable
@ToString
@Data
public class Username {

    private String username;

    public Username() {}

    public Username(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
