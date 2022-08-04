package org.zchzh.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zchzh.test.dto.UserDTO;
import org.zchzh.test.request.LoginReq;
import org.zchzh.test.service.UserService;

/**
 * @author zengchzh
 * @date 2022/8/3
 */

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public UserDTO login(@RequestBody LoginReq req) {
        return userService.login(req);
    }

}
