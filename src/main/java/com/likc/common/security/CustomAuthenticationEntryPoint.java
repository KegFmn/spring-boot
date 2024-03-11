package com.likc.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.likc.common.lang.Result;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        Result<Void> error = Result.error(401, "用户没有该权限");
        String json = objectMapper.writeValueAsString(error);
        response.setStatus(200);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().println(json);
    }
}