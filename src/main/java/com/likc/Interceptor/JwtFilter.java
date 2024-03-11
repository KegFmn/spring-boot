package com.likc.Interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.likc.common.lang.LoginUser;
import com.likc.mapper.RoleMapper;
import com.likc.po.User;
import com.likc.service.UserService;
import com.likc.util.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Resource
    private JwtUtils jwtUtils;

    @Resource
    private UserService userService;

    @Resource
    private RoleMapper roleMapper;


    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //获取请求头中的token：从HttpServletRequest对象中获取名为"token"的请求头信息。
        String token = httpServletRequest.getHeader("Authorization");

        //检查token是否存在：如果token不存在，则直接放行请求，继续执行后续的过滤器或处理器。
        if(StringUtils.isBlank(token)){
            //放行操作
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }

        DecodedJWT verify = jwtUtils.verify(token);
        String userId = verify.getClaim("id").asString();

        User user = userService.lambdaQuery().eq(User::getId, userId).one();
        Assert.notNull(user, "用户不存在");

        List<String> perms = roleMapper.findPermsByUserId(user.getId());
        LoginUser loginUser = new LoginUser(user, perms);

        /**
         * 需要有一个Authentication类型的数据，所以先转换类型
         * UsernamePasswordAuthenticationToken参数解析如下：
         * principal：表示身份验证的主体，通常是用户的唯一标识，比如用户名、用户ID等。
         * credentials：表示身份验证的凭证，通常是用户的密码或其他认证信息。
         * authorities：表示用户的权限集合，即用户被授予的权限列表
         */
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());

        //存入SecurityContextHolder：将获取到的用户信息存入SecurityContextHolder中，以便后续的权限验证和授权操作。
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //放行
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}