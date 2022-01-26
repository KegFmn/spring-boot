package com.likc.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author likc
 * @since 2022-01-25
 */
@RestController
@RequestMapping("/likc/student")
public class StudentController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
}
