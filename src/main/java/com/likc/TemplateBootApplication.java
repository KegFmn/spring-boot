package com.likc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@MapperScan("com.likc.mapper")
public class TemplateBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(TemplateBootApplication.class, args);
    }

}
