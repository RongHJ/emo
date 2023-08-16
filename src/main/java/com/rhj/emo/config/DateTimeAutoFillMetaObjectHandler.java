package com.rhj.emo.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class DateTimeAutoFillMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入时填充策略
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        System.out.println("插入时策略");
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("deleted",0,metaObject);
    }

    /**
     * 更新时填充策略
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
