package com.likc.shiro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: likc
 * @Date: 2022/02/15/21:46
 * @Description: 返回前端的封装用户信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountProfile implements Serializable {

    private Long id;

    private String userName;

    private String avatar;

    private String email;

}
