package com.rhj.emo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(value = "com.rhj.emo.mapper")
@ComponentScan(basePackages = {"com.rhj"})
public class EmoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmoApplication.class, args);
    }

}
