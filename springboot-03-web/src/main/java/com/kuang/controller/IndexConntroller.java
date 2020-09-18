package com.kuang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

// 在templates目录下的所有页面，只能通过controller来控制
// 需要模版引擎的支持thymeleaf

@Controller
public class IndexConntroller {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/test")
    public String test(Model model) {
        model.addAttribute("msg","<h1>hello springboot!</h1>");

        model.addAttribute("users", Arrays.asList("fangfang","mingming"));
        return "test";
    }
}
