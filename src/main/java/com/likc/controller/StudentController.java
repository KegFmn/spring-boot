package com.likc.controller;


import com.likc.common.lang.Result;
import com.likc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author likc
 * @since 2022-02-15
 */
@RestController
@RequestMapping("/likc/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/hello")
    public Result<Void> hello(){

        studentService.aysc();

        return new Result<>(200, "请求成功");

    }

}
