package org.zchzh.test.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author zengchzh
 * @date 2022/8/4
 */

@Data
@Builder
public class CreateReq implements ParamRequest {

    private String username;

    private String password;

    @Override
    public void check() {
        if (StringUtils.isEmpty(username)) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        if (StringUtils.isEmpty(password)) {
            throw new IllegalArgumentException("密码不能为空");
        }
        if (!checkPwd()) {
            throw new IllegalArgumentException("密码必须包含大小写字母和数字，长度在4-10");
        }
    }

    private static final Pattern PATTERN = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]{4,10}$");

    private boolean checkPwd() {
        return PATTERN.matcher(password).matches();
    }
}
