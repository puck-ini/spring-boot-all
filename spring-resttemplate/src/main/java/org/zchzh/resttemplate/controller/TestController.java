package org.zchzh.resttemplate.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zchzh.resttemplate.model.Result;
import org.zchzh.resttemplate.model.TestReq;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zengchzh
 * @date 2021/9/14
 */

@RestController
public class TestController {



    @GetMapping("/v1/get")
    public Result getv1(TestReq req) {
        return new Result();
    }

    @GetMapping("/v2/get")
    public Result getv2(TestReq req, HttpServletRequest request) {
        String data = request.getHeader("test");
        return new Result(req.getMsg() + data);
    }
//
//    @GetMapping("/v3/get")
//    public Result getv3(@RequestBody TestReq req) {
//        return new Result(req.getMsg());
//    }

    @PostMapping("/v1/post")
    public Result postv1(TestReq req) {
        return new Result(req.getMsg());
    }

    @PostMapping("/v2/post")
    public Result postv2(TestReq req, HttpServletRequest request) {
        String data = request.getHeader("test");
        return new Result(req.getMsg() + data);
    }

    @PostMapping("/v3/post")
    public Result postv3(@RequestBody TestReq req) {
        return new Result(req.getMsg());
    }


    @PutMapping("/put")
    public Result put(@RequestBody TestReq req) {
        return new Result(req.getMsg());
    }

    @DeleteMapping("/delete")
    public Result delete(TestReq req) {
        return new Result();
    }

    @PostMapping("/upload")
    public Result upload(@RequestParam("msg") String msg, @RequestParam("files") MultipartFile[] files) {
        return new Result("size: " + files.length);
    }
}
