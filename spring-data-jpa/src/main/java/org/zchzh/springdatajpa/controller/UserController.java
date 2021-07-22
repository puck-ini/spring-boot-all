package org.zchzh.springdatajpa.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.zchzh.springdatajpa.convert.UserConvert;
import org.zchzh.springdatajpa.dto.UserDTO;
import org.zchzh.springdatajpa.entity.UserDetail;
import org.zchzh.springdatajpa.entity.UserEntity;
import org.zchzh.springdatajpa.request.SearchReq;
import org.zchzh.springdatajpa.service.UserService;
import org.zchzh.springdatajpa.types.Username;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zengchzh
 * @date 2021/5/10
 */

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public List<UserEntity> getAll() {
        return userService.listAll();
    }

    @GetMapping("/page")
    public Page<UserEntity> page() {
        List<Long> ids = new ArrayList<>();
        for (long i = 0L; i < 10; i++) {
            ids.add(i);
        }
        return userService.listAll(ids, PageRequest.of(0, 3));
    }

    @GetMapping("/sort")
    public List<UserEntity> sort() {
        List<Long> ids = new ArrayList<>();
        for (long i = 0L; i < 10; i++) {
            ids.add(i);
        }
        return userService.listAll(ids, Sort.by(Sort.Direction.DESC,"id"));
    }

    @PostMapping("/add")
    public UserDTO add() {
        UserEntity userEntity = UserEntity.newAdmin().username("test").password("Test12345******");
        UserEntity userEntity1 = userService.create(userEntity);
        UserEntity userEntity2 = new UserEntity();
        UserConvert.toEntity(UserConvert.toDto(userEntity1));
        return UserConvert.toDto(userEntity);
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/v2/add")
    public UserEntity addV2() {
        UserEntity userEntity = UserEntity.newAdmin().username("test").password("Test12345******");
        UserDetail userDetail = new UserDetail();
        userDetail.setName("qwe");
        userDetail.setAddress("123");
        userDetail.setAge(12);
        userEntity.setUserDetail(userDetail);
        return userService.create(userEntity);
    }

    @PutMapping("/update")
    public UserEntity update(Long id) {
        UserEntity userEntity = userService.get(id).get();
        userEntity.getUserDetail().setAge(789);
        return userService.update(userEntity);
    }

    @DeleteMapping("/remove/detail")
    public UserEntity removeDetail(Long id) {
        UserEntity userEntity = userService.get(id).get();
        userEntity.setUserDetail(null);
        return userService.update(userEntity);
    }

    @DeleteMapping("/remove")
    public UserEntity remove(Long id) {
        return userService.remove(id);
    }

    @GetMapping("/username")
    public List<UserEntity> getAllByUsername() {
        return userService.findAllByUsername(new Username("test"));
    }

    @GetMapping("/test")
    public List<UserDTO> test() {
        return userService.findAllByUsername(new Username("test"), UserDTO.class);
    }

    @GetMapping("/search")
    public Page<UserEntity> search(SearchReq req) {
        return userService.search(req);
    }
}
