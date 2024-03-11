package com.likc.common.exception;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.likc.common.lang.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: likc
 * @Date: 2022/01/04/23:28
 * @Description: 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 运行时异常比如：空指针、类型转换、数组下标越界
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
     public Result<Object> handler(RuntimeException e){
         log.error("运行时异常: ======================={}",e.getMessage());
         return Result.error(400, e.getMessage());
     }

    /**
     *  Assert断言异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result<Object> handler(IllegalArgumentException e){
        log.error("Assert异常: ======================={}",e.getMessage());
        return Result.error(400, e.getMessage());
    }

    /**
     * 搭配@Validated和Valid校验前端参数
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<Object> handler(MethodArgumentNotValidException e){
        log.error("实体校验异常: ======================={}", e.getMessage());
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        return Result.error(400, objectError.getDefaultMessage());
    }

}
