package com.rhj.emo.controller;

import com.rhj.emo.entity.User;
import com.rhj.emo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("user/getAllUser")
    public List<User> getAllUsers(){
        List<User> list = userService.getAllUser();
        return list;

    }

}
