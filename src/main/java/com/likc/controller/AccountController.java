package com.likc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.likc.common.dto.LoginDto;
import com.likc.common.lang.Result;
import com.likc.po.User;
import com.likc.service.UserService;
import com.likc.util.JwtUtils;
import com.likc.vo.LoginVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @CrossOrigin
    @PostMapping("/login")
    public Result<LoginVo> login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response){

        User user = userService.getOne(new QueryWrapper<User>().eq("user_name", loginDto.getUserName()));
        Assert.notNull(user,"用户不存在");

        if (!user.getPassWord().equals(DigestUtils.md5Hex(loginDto.getPassWord()))){
            return new Result<>(400, "密码不正确");
        }
        HashMap<String, String> payload = new HashMap<>(16);
        payload.put("id", user.getId().toString());
        String jwt = jwtUtils.createJwt(payload);
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");

        LoginVo loginVo = new LoginVo();
        BeanUtils.copyProperties(user, loginVo);

        return new Result<>(200, "请求成功", loginVo);
    }

    @RequiresAuthentication
    @GetMapping("/logout")
    public Result<Object> logout(){
        SecurityUtils.getSubject().logout();
        return new Result<>(200, "请求成功");
    }

    @RequestMapping("/shiro")
    public void expired(HttpServletRequest request) {
        Object expired = request.getAttribute("expired");
        if (expired != null) {
            throw new ExpiredCredentialsException((String) expired);
        }

        Object unsupport = request.getAttribute("unsupport");
        if (unsupport != null) {
            throw new UnsupportedTokenException((String) unsupport);
        }
    }

}
