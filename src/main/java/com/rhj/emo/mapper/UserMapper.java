package com.rhj.emo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rhj.emo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT COUNT(*) FROM USER")
    public int getCount();
}
