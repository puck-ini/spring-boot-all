package org.zchzh.test.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.zchzh.test.dto.UserDTO;
import org.zchzh.test.entity.User;
import org.zchzh.test.repo.UserRepo;
import org.zchzh.test.request.LoginReq;
import org.zchzh.test.service.VerifyCodeService;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author zengchzh
 * @date 2022/8/4
 */


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest1 {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private VerifyCodeService verifyCodeService;

    @Autowired
    private UserRepo userRepo;

    @BeforeEach
    void initTestData() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("Test123");
        userRepo.save(user);
    }

    /**
     * 使用 restTemplate 的方式使用 @Transactional 和 @Rollback 会导致 @BeforeEach 无法添加测试数据
     * @throws JsonProcessingException
     */
//    @Transactional
//    @Rollback
    @Test
    void httpLoginSuccess() throws JsonProcessingException {
        LoginReq req = LoginReq.builder().username("test").password("Test123").code(verifyCodeService.getCode()).build();
        UserDTO expect = UserDTO.builder().username("test").build();
        String jsonReq = mapper.writeValueAsString(req);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(jsonReq, httpHeaders);
        String url = "http://127.0.0.1:" + port + "/user/login";
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        String jsonResult = result.getBody();
        UserDTO actual = mapper.readValue(jsonResult, UserDTO.class);
        assertEquals(expect.getUsername(), actual.getUsername());
    }

    @Autowired
    private MockMvc mockMvc;

    @Transactional
    @Rollback
    @Test
    void httpLogin() throws Exception {
        LoginReq req = LoginReq.builder().username("test").password("Test123").code(verifyCodeService.getCode()).build();
        String jsonReq = mapper.writeValueAsString(req);
        String url = "/user/login";
        UserDTO expect = UserDTO.builder().username("test").build();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url).content(jsonReq).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String jsonResult = mvcResult.getResponse().getContentAsString();
        UserDTO actual = mapper.readValue(jsonResult, UserDTO.class);
        assertEquals(expect.getUsername(), actual.getUsername());
    }
}
