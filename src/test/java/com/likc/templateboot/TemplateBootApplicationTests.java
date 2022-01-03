package com.likc.templateboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
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

}
