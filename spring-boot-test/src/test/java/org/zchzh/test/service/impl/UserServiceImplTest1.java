package org.zchzh.test.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.zchzh.test.dto.UserDTO;
import org.zchzh.test.entity.User;
import org.zchzh.test.repo.UserRepo;
import org.zchzh.test.request.CreateReq;
import org.zchzh.test.request.LoginReq;
import org.zchzh.test.service.UserService;
import org.zchzh.test.service.VerifyCodeService;

import java.util.Objects;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest
class UserServiceImplTest1 {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private VerifyCodeService verifyCodeService;



    @BeforeEach
    void initTestData() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        userRepo.save(user);
    }

    /**
     * 测试成功流程
     */
    @Test
    @DisplayName("登陆成功测试")
    void loginSuccess() {
        LoginReq req = LoginReq.builder().username("test").password("test").code(verifyCodeService.getCode()).build();
        UserDTO expect = UserDTO.builder().username("test").build();
        UserDTO actual = userService.login(req);
        assertEquals(expect.getUsername(), actual.getUsername());
    }

    /**
     * 测试失败流程
     */
    @Test
    @DisplayName("登录失败测试")
    void loginFail() {
        LoginReq req = LoginReq.builder().username("test").password("test123").code(verifyCodeService.getCode()).build();
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> userService.login(req));
        assertEquals("用户名或密码错误", e.getMessage());
    }

    @Test
    @DisplayName("登录验证码错误测试")
    void loginFail1() {
        LoginReq req = LoginReq.builder().username("test").password("test").code("&*(&*(").build();
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> userService.login(req));
        assertEquals("验证码错误", e.getMessage());
    }

    @Test
    @DisplayName("登录失败限制测试")
    void loginFail2() {
        IntStream.range(0, 5).forEach(i -> {
            LoginReq req = LoginReq.builder().username("test").password("test123").code(verifyCodeService.getCode()).build();
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> userService.login(req));
            assertEquals("用户名或密码错误", e.getMessage());
        });
        LoginReq req = LoginReq.builder().username("test").password("test123").code(verifyCodeService.getCode()).build();
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> userService.login(req));
        assertEquals("当前用户已被锁定", e.getMessage());
    }



    @Test
    @Transactional
    @Rollback
    @DisplayName("创建用户成功")
    void createSuccess() {
        CreateReq req = CreateReq.builder().username("test").password("Test123").build();
        UserDTO expect = UserDTO.builder().id(1L).username("test").build();
        UserDTO actual = userService.create(req);
        assertEquals(expect, actual);
    }

    @Test
    @Transactional
    @Rollback
    @DisplayName("创建用户失败")
    void createFail() {
        CreateReq req = CreateReq.builder().username("test").password("test").build();
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> userService.create(req));
        assertEquals("密码必须包含大小写字母和数字，长度在4-10", e.getMessage());
    }

    @Test
    void list() {
        log.info(userRepo.findAll().toString());
    }
}