package com.likc.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.likc.common.lang.Result;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Result<Void> error = Result.error(401, "用户授权失败");
        String json = objectMapper.writeValueAsString(error);
        response.setStatus(200);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().println(json);
    }

}
