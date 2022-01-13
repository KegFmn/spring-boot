package com.likc.templateboot;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // websocket测试必加，因为需要真实的tomcat
class TemplateBootApplicationTests {

    @Test
    void contextLoads() {
        String str = "2";
        //System.out.println(str.length() - 1);
        //System.out.println(str.charAt(str.length() - 1));
        //System.out.println(str.charAt(0) - '0');
        System.out.println((int)str.charAt(0));
        System.out.println((int)'0');
    }

    @Test
    void logTest() {
        log.info("我是info日志");
        log.warn("我是warn日志");
        log.error("我是error日志");
    }
}
