package org.zchzh.test.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.zchzh.test.dto.UserDTO;
import org.zchzh.test.request.LoginReq;
import org.zchzh.test.service.UserService;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @InjectMocks
    private UserController userController = new UserController();

    @Mock
    private UserService userService;

    private LoginReq loginReq = LoginReq.builder().username("test").password("Test1").build();

    private UserDTO userDTO = UserDTO.builder().id(1L).build();

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);

        Mockito.when(userService.login(loginReq)).thenAnswer(new Answer<UserDTO>() {
            @Override
            public UserDTO answer(InvocationOnMock invocation) throws Throwable {
                LoginReq req = invocation.getArgument(0, LoginReq.class);
                userDTO.setUsername(req.getUsername());
                return userDTO;
            }
        });

    }
    @Test
    void login() {
        UserDTO actual = userController.login(loginReq);
        assertEquals(userDTO, actual);
    }
}