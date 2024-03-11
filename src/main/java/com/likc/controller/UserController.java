package com.likc.controller;


import com.likc.domain.LoginUser;
import com.likc.dto.LoginDTO;
import com.likc.common.lang.Result;
import com.likc.po.User;
import com.likc.service.UserService;
import com.likc.util.JwtUtils;
import com.likc.vo.LoginVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public Result<LoginVo> login(@Validated @RequestBody LoginDTO loginDto, HttpServletResponse response){
        //创建一个UsernamePasswordAuthenticationToken对象，将用户的用户名和密码作为参数传入。
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassWord());

        //调用authenticationManager.authenticate()方法对用户进行身份验证，返回一个Authentication对象。
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        Assert.notNull(authenticate,"用户不存在");

        //从authenticate对象中获取登录用户的信息。
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();

        User user = loginUser.getUser();
        HashMap<String, String> payload = new HashMap<>(16);
        payload.put("id", user.getId().toString());

        response.setHeader("Authorization", jwtUtils.createJwt(payload));
        response.setHeader("Access-Control-Expose-Headers", "Authorization");

        LoginVo loginVo = new LoginVo();
        BeanUtils.copyProperties(user, loginVo);

        return Result.success(loginVo);
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('user:get')")
    public Result<Void> get() {
        return Result.success();
    }
}
