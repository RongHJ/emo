package com.rhj.emo;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.Digester;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rhj.emo.entity.User;
import com.rhj.emo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmoApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {
//        User user = new User();
//        user.setUserid("9999");
//        user.setUname("管理员");
//        Digester md5 = new Digester(DigestAlgorithm.MD5);
//        String pwd_encode =  md5.digestHex("123456");
//        user.setPassword(pwd_encode);
//        userMapper.insert(user);
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.eq("USERID","9998");
//        userMapper.delete(wrapper);

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("USERID","9998");
        Integer count = userMapper.selectCount(wrapper);
        System.out.println(count);
    }

}
