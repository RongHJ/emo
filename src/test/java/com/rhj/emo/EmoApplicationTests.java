package com.rhj.emo;

import cn.hutool.crypto.digest.DigestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmoApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(DigestUtil.md5("123456"));

    }

}
