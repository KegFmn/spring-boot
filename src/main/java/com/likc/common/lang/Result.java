package com.likc.common.lang;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: likc
 * @Date: 2022/01/04/23:28
 * @Description: 结果集
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {

    private Integer code;
    private String msg;
    private T data;

    public static <T> Result<T> success() {
        return new Result<>(200, "success", null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(200, "success", data);
    }

    public static <T> Result<T> error(Integer code, String message) {
        return new Result<>(code, message, null);
    }

}
