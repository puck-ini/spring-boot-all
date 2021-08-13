package org.zchzh.swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author zengchzh
 * @date 2021/8/13
 */

@Api(value = "测试接口", tags = "测试接口1.0")
@RestController
public class TestController {


    @ApiOperation(value = "获取信息", notes = "获取信息备注")
    @GetMapping("/msg")
    public String getMsg() {
        return "hello swagger";
    }

    @ApiOperation(value = "发送信息", notes = "发送信息备注")
    @ApiImplicitParams({@ApiImplicitParam(name = "msg", value = "消息", required = true)})
    @PostMapping("/send")
    public String send(String msg) {
        return msg;
    }

    @ApiOperation(value = "测试put", notes = "测试put备注")
//    @ApiImplicitParams({@ApiImplicitParam(name = "msg", value = "消息", required = true)})
    @PutMapping("/put")
    public String put(String msg) {
        return msg;
    }

    @ApiOperation(value = "测试delete", notes = "测试delete备注")
    @ApiImplicitParams({@ApiImplicitParam(name = "msg", value = "消息", required = true)})
    @DeleteMapping("/delete")
    public String delete(String msg) {
        return msg;
    }
}
