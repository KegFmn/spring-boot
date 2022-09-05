package com.likc.shiro;

import lombok.*;

import java.io.Serializable;

/**
 * @Author: likc
 * @Date: 2022/02/15/21:46
 * @Description:
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountProfile implements Serializable {

    private Long id;

    private String userName;

    private String avatar;

    private String email;

}
