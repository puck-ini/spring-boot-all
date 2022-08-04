package org.zchzh.test.service;

import org.zchzh.test.dto.UserDTO;
import org.zchzh.test.request.CreateReq;
import org.zchzh.test.request.LoginReq;

/**
 * @author zengchzh
 * @date 2022/8/3
 */
public interface UserService {


    UserDTO login(LoginReq req);


    UserDTO create(CreateReq req);
}
