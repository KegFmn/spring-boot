package com.likc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.likc.domain.LoginUser;
import com.likc.mapper.RoleMapper;
import com.likc.mapper.UserMapper;
import com.likc.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", s);

        User user = userMapper.selectOne(wrapper);
        Assert.notNull(user, "用户不存在");

        //TODO 根据用户查询权限信息 添加到LoginUser中
        List<String> perms = roleMapper.findPermsByUserId(user.getId());

        //封装成UserDetails对象返回
        return new LoginUser(user, perms);
    }
}