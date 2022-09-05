package com.likc.shiro.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.likc.common.lang.Result;
import com.likc.shiro.JwtToken;
import com.likc.util.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: likc
 * @Date: 2022/02/15/20:36
 * @Description: shiro过滤器
 */
@Slf4j
@Component
public class JwtFilter extends AuthenticatingFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     *  请求拦截，当头部没有Authorization时候，直接通过，不需要自动登录；当带有的时候，校验jwt的有效性，没问题就直接执行executeLogin方法实现自动登录
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        log.info("----->请求拦截");
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String jwt = request.getHeader("Authorization");
        // 当请求头的token为空，放行去Controller校验权限
        if (StringUtils.isEmpty(jwt)){
            return true;
        }else {
            // 校验jwt
            Claims claim = jwtUtils.getClaimByToken(jwt);
            if (claim == null || jwtUtils.isTokenExpired(claim.getExpiration())){
                throw new ExpiredCredentialsException("token已失效，请重新登录");
            }

            // 去到createToken()生成jwt之后到AccountRealm的doGetAuthenticationInfo()作身份校验
            return executeLogin(servletRequest,servletResponse);
        }
    }

    /**
     *  实现登录生成我们自定义支持的JwtToken
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        log.info("----->生成jwt");
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String jwt = request.getHeader("Authorization");
        // 当请求头的token为空,，返回空否则生成jwt
        if (StringUtils.isEmpty(jwt)){
            return null;
        }

        return new JwtToken(jwt);
    }


    /**
     *  登录异常时候进入的方法，直接把异常信息封装然后抛出
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        log.info("onLoginFailure");
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;

        Throwable throwable = e.getCause() == null ? e : e.getCause();

        Result<Void> result = new Result<>(400, throwable.getMessage());

        try {
            String json = objectMapper.writeValueAsString(result);
            httpServletResponse.getWriter().print(json);
        } catch (Exception exception) {

        }

        return false;
    }

    /**
     * 拦截器的前置拦截，对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {

        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个OPTIONS请求，这里我们给OPTIONS请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(org.springframework.http.HttpStatus.OK.value());
            return false;
        }

        return super.preHandle(request, response);
    }
}
