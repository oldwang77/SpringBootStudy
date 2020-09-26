package com.kuang.swagger.controller;

import com.kuang.swagger.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @Api...都是写注释
@RestController
public class HelloController {

    @RequestMapping(value = "/hello")
    public String Hello(){return "hello";}

    // 只要我们的接口中，返回值中存在实体类，他就会被扫描到swagger中
    @PostMapping(value = "/user")
    public User user(){return new User();}

    // Operation接口，不是放在类上的，是放在方法上的
    @ApiOperation("Hello控制类")
    @GetMapping(value = "/hello2")
    public String hello(@ApiParam("用户名") String username){return "hello"+username;}

    @ApiOperation("get测试")
    @GetMapping("/get")
    public User hello2(@ApiParam("用户") User user) {
        return user;
    }
}
