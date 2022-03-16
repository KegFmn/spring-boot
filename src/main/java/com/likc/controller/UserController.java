package com.likc.controller;


import com.likc.common.lang.Result;
import com.likc.po.User;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
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
@RequestMapping("/user")
public class UserController {

    @RequiresAuthentication
    @GetMapping("/index")
    public Result<User> index(){
        User user = new User();
        user.setUserName("luozhenni");
        return new Result<>(200, "成功", user);
    }
}
