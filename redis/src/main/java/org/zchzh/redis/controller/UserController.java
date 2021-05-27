package org.zchzh.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zchzh.redis.entity.User;
import org.zchzh.redis.service.UserService;

import java.util.List;

/**
 * @author zengchzh
 * @date 2020/9/22 11:18
 * @description TODO
 */

@RestController
public class UserController {


    @Autowired
    private UserService userService;



    @GetMapping("/get")
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/getone")
    public User getOne(@RequestParam("id") Long id){
        return userService.getOne(id);
    }


    @PostMapping("/create")
    public User insert(@RequestBody User myUser){
        return userService.create(myUser);
    }

    @PutMapping("/update")
    public User update(@RequestBody User myUser){
        return userService.update(myUser);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam("id") Long id){
        userService.remove(id);
    }
}
