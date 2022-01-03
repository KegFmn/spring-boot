package com.likc.templateboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.likc.templateboot.mapper")
public class TemplateBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(TemplateBootApplication.class, args);
    }

}
