package com.kuang.controller;

import com.kuang.mapper.UserMapper;
import com.kuang.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/queryUserByList")
    public List<User> queryUserList(){
        List<User> users = userMapper.queryUserList();
        for(User user:users){
            System.out.println(user);
        }
        return users;
    }

    //添加一个用户
    @GetMapping("/addUser")
    public String addUser() {
        userMapper.addUser(new User(7,"阿毛","123456"));
        return "ok";
    }

    //修改一个用户
    @GetMapping("/updateUser")
    public String updateUser() {
        userMapper.updateUser(new User(7,"阿毛","123456"));
        return "ok";
    }

    @GetMapping("/deleteUser")
    public String deleteUser() {
        userMapper.deleteUser(1);
        return "ok";
    }
}
