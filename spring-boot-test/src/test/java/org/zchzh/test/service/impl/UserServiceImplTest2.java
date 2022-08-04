package org.zchzh.test.service.impl;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.zchzh.test.convert.UserConvert;
import org.zchzh.test.dto.UserDTO;
import org.zchzh.test.entity.User;
import org.zchzh.test.repo.UserRepo;
import org.zchzh.test.request.CreateReq;
import org.zchzh.test.request.LoginReq;
import org.zchzh.test.service.CacheService;
import org.zchzh.test.service.UserService;
import org.zchzh.test.service.VerifyCodeService;


import static org.junit.jupiter.api.Assertions.*;
/**
 * @author zengchzh
 * @date 2022/8/3
 */

@Slf4j
public class UserServiceImplTest2 {

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @Mock
    private UserRepo userRepo;

    @Mock
    private CacheService cacheService;

    @Mock
    private VerifyCodeService verifyCodeService;

    private final User testUser = User.builder().id(1L).username("test").password("Test123").build();

    @BeforeEach
    void initTest() {
        MockitoAnnotations.initMocks(this);

        Mockito.when(cacheService.get(testUser.getUsername())).thenReturn(RandomUtil.randomInt(0, 4));
        Mockito.doNothing().when(cacheService).set(Mockito.anyString(), Mockito.any());
        Mockito.doNothing().when(cacheService).remove(Mockito.anyString());
        Mockito.when(verifyCodeService.verify(Mockito.anyString())).thenReturn(true);
        Mockito.when(userRepo.findByUsername(testUser.getUsername())).thenReturn(testUser);
        Mockito.when(userRepo.save(Mockito.any())).thenReturn(testUser);

    }


    @Test
    @DisplayName("登录成功")
    void loginIfResultIsOK() {
        LoginReq req = LoginReq.builder().username(testUser.getUsername()).password(testUser.getPassword()).code("123").build();
        UserDTO actual = userService.login(req);
        UserDTO expect = UserConvert.CONVERT.toDTO(testUser);
        assertEquals(expect, actual);
        Mockito.verify(cacheService).get(testUser.getUsername());
        Mockito.verify(cacheService).remove(Mockito.anyString());
        Mockito.verify(verifyCodeService).verify(Mockito.anyString());
        Mockito.verify(userRepo).findByUsername(testUser.getUsername());
    }

    @Test
    @DisplayName("用户名不存在")
    void loginIfUsernameNotExist() {
        LoginReq req = LoginReq.builder().username("1").password(testUser.getPassword()).code("123").build();
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> userService.login(req));
        assertEquals("用户名或密码错误", e.getMessage());

    }

    @Test
    @DisplayName("密码错误")
    void loginIfPasswordIsWrong() {
        LoginReq req = LoginReq.builder().username(testUser.getUsername()).password("1").code("123").build();
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> userService.login(req));
        assertEquals("用户名或密码错误", e.getMessage());
    }

    @Test
    @DisplayName("验证码错误")
    void loginIfVerifyCodeIsWrong() {
        Mockito.when(verifyCodeService.verify(Mockito.anyString())).thenReturn(false);
        LoginReq req = LoginReq.builder().username(testUser.getUsername()).password("1").code("123").build();
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> userService.login(req));
        assertEquals("验证码错误", e.getMessage());
    }

    @Test
    @DisplayName("用户被锁定")
    void loginIfUserIsLocked() {
        Mockito.when(cacheService.get(testUser.getUsername())).thenReturn(5);
        LoginReq req = LoginReq.builder().username(testUser.getUsername()).password("1").code("123").build();
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> userService.login(req));
        assertEquals("当前用户已被锁定", e.getMessage());
    }

    @Test
    @DisplayName("登录名为空")
    void loginIfUsernameIsNull() {
        LoginReq req1 = LoginReq.builder().password(testUser.getPassword()).code("123").build();
        IllegalArgumentException e1 = assertThrows(IllegalArgumentException.class, () -> userService.login(req1));
        assertEquals("用户名不能为空", e1.getMessage());


        LoginReq req2 = LoginReq.builder().username("").password(testUser.getPassword()).code("123").build();
        IllegalArgumentException e2 = assertThrows(IllegalArgumentException.class, () -> userService.login(req2));
        assertEquals("用户名不能为空", e2.getMessage());
    }

    @Test
    @DisplayName("密码为空")
    void loginIfPasswordIsNull() {
        LoginReq req1 = LoginReq.builder().username(testUser.getUsername()).code("123").build();
        IllegalArgumentException e1 = assertThrows(IllegalArgumentException.class, () -> userService.login(req1));
        assertEquals("密码不能为空", e1.getMessage());


        LoginReq req2 = LoginReq.builder().username(testUser.getUsername()).password("").code("123").build();
        IllegalArgumentException e2 = assertThrows(IllegalArgumentException.class, () -> userService.login(req2));
        assertEquals("密码不能为空", e2.getMessage());
    }

    @Test
    @DisplayName("验证码为空")
    void loginIfCodeIsNull() {
        LoginReq req1 = LoginReq.builder().username(testUser.getUsername()).password(testUser.getPassword()).build();
        IllegalArgumentException e1 = assertThrows(IllegalArgumentException.class, () -> userService.login(req1));
        assertEquals("验证码不能为空", e1.getMessage());

        LoginReq req2 = LoginReq.builder().username(testUser.getUsername()).password(testUser.getPassword()).code("").build();
        IllegalArgumentException e2 = assertThrows(IllegalArgumentException.class, () -> userService.login(req2));
        assertEquals("验证码不能为空", e2.getMessage());
    }

    @Test
    @DisplayName("创建用户")
    void createUserIfResultIsOK() {
        CreateReq req = CreateReq.builder().username(testUser.getUsername()).password(testUser.getPassword()).build();
        UserDTO expect = UserConvert.CONVERT.toDTO(testUser);
        UserDTO actual = userService.create(req);
        assertEquals(expect, actual);
    }

    @Test
    @DisplayName("创建用户密码校验失败")
    void createUserIfPasswordVerifyIsWrong() {
        CreateReq req = CreateReq.builder().username(testUser.getUsername()).password("tt123").build();
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> userService.create(req));
        assertEquals("密码必须包含大小写字母和数字，长度在4-10", e.getMessage());
    }
}
