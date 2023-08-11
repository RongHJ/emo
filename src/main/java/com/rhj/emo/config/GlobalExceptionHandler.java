package com.rhj.emo.config;

import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler{

    /**
     * 处理Exception异常
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    SaResult handleException(Exception e){

        return SaResult.error(e.getMessage());
    }

}
