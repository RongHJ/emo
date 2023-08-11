package com.rhj.emo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rhj.emo.entity.User;
import com.rhj.emo.mapper.UserMapper;
import com.rhj.emo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{


    @Override
    public List<User> getAllUser() {

        List<User> users = baseMapper.selectList(null);
        System.out.println("总数："+baseMapper.getCount());

        return users;
    }
}
