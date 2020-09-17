package com.kuang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 在templates目录下的所有页面，只能通过controller来控制
// 需要模版引擎的支持thymeleaf

@Controller
public class IndexConntroller {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
