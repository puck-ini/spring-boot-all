package org.zchzh.springdatajpa.types;

import lombok.ToString;

import javax.persistence.Embeddable;

/**
 * @author zengchzh
 * @date 2021/5/4
 */

@Embeddable
@ToString
public class Password {

    private String password;

    private static final String VALID_STRING = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$";

    public Password() {}

    public Password(String password) {
        valid(password);
        this.password = password;
    }

    private void valid(String password) {
        if (password == null || "".equals(password)) {
            throw new NullPointerException("密码不能为空或者是空字符串");
        }
        if (!password.matches(VALID_STRING)) {
            throw new IllegalArgumentException("密码长度8-16位，必须包含数字、大小写字母、符号");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        valid(password);
        this.password = password;
    }
}
