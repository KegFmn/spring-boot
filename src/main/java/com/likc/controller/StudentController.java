package com.likc.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author likc
 * @since 2022-01-04
 */
@Api(tags="学生管理")
@RestController
@RequestMapping("/student")
public class StudentController {

    @ApiOperation("得到hello")
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
