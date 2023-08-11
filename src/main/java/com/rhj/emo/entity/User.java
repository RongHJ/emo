package com.rhj.emo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 132453L;

    private String userid;
    private String uname;
    private String password;

    private Integer sex;
    private Integer age;
    private String address;
    private String tel;
    private String email;
    @TableField(fill = FieldFill.INSERT)//自动填充
    private String create_time;
    @TableField(fill = FieldFill.INSERT_UPDATE)

    private String update_time;
    @TableLogic
    private Integer isDeleted;

}
