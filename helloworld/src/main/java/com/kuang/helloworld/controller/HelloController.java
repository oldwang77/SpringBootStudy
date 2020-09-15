package com.kuang.helloworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// restcontroller返回的是字符串
@RestController
public class HelloController {

    // 接口：http://localhost:8080/hello
    @RequestMapping("/hello")
    public String Hello() {
        // 调用业务，接受前端的参数
        return "hello world";
    }
}
