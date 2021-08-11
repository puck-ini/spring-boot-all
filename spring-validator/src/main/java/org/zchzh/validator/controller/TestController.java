package org.zchzh.validator.controller;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zchzh.validator.request.TestReq;

/**
 * @author zengchzh
 * @date 2021/8/11
 */

@RestController
public class TestController {


    /**
     * BindException
     * @param req
     * @return
     */
    @GetMapping("/test")
    public TestReq test(@Validated TestReq req) {
        return req;
    }


    /**
     * MethodArgumentNotValidException
     * @param req
     * @return
     */
    @PostMapping("/test1")
    public TestReq test1(@Validated @RequestBody TestReq req) {
        return req;
    }


    @GetMapping("/test2")
    public String test2(@Validated @RequestParam("msg") @Length(min = 1, max = 5) String msg) {
        return msg;
    }
}
