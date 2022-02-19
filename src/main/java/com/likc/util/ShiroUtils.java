package com.likc.util;

import com.likc.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 * @Author: likc
 * @Date: 2022/02/19/20:36
 * @Description: shiro工具类
 */
public class ShiroUtils {

    public static AccountProfile getProfile(){
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}
