package com.rhj.emo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 132453L;
   // @TableId
    private String userid;
    private String uname;
    private String password;

    private Integer sex;
    private Integer age;
    private String address;
    private String tel;
    private String email;
    @TableField(fill = FieldFill.INSERT)//自动填充
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

}
