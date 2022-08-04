package org.zchzh.test.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @author zengchzh
 * @date 2022/8/3
 */

@Data
@Builder
public class LoginReq implements ParamRequest {

    private String username;

    private String password;

    private String code;

    @Override
    public void check() {
        if (StringUtils.isEmpty(username)) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        if (StringUtils.isEmpty(password)) {
            throw new IllegalArgumentException("密码不能为空");
        }
        if (StringUtils.isEmpty(code)) {
            throw new IllegalArgumentException("验证码不能为空");
        }
    }
}
