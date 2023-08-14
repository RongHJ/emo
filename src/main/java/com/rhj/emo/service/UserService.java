package com.rhj.emo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rhj.emo.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    //获取所有用户
    List<User> getAllUser();

    boolean isExistUser(String userid);

    User getUserById(String userid);



}
