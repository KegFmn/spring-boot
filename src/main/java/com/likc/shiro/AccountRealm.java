package com.likc.shiro;

import com.auth0.jwt.interfaces.Claim;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.likc.po.User;
import com.likc.service.UserService;
import com.likc.shiro.AccountProfile;
import com.likc.shiro.JwtToken;
import com.likc.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: likc
 * @Date: 2022/02/15/20:15
 * @Description: shiro权限控制器
 */
@Slf4j
@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        // 判断token是否jwt的token
        return token instanceof JwtToken;
    }

    /**
     *  身份验证（密码校验）
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        JwtToken jwtToken = (JwtToken)token;

        String userId = jwtUtils.verify((String) jwtToken.getPrincipal()).getClaim("id").asString();
        User user = userService.getById(Long.parseLong(userId));

        if (user == null){
            throw new UnknownAccountException("账户不存在");
        }

        if (user.getStatus() == -1){
            throw new LockedAccountException("账户已被锁定");
        }

        AccountProfile profile = new AccountProfile();
        BeanUtils.copyProperties(user, profile);

        //profile消息体、getCredentials获取密钥、getName()其实就是这个自定义类的名字，校验成功后放行到Controller
        return new SimpleAuthenticationInfo(profile, jwtToken.getCredentials(), getName());
    }

    /**
     *  授权
     *  只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("授权");
        // 数据库获取权限
        /*Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
        AccountProfile accountProfile=(AccountProfile) primaryPrincipal;
        User user = userService.getOne(new QueryWrapper<User>().eq("userName", accountProfile.getUserName()));
        if(user != null){

            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            Set<String> set= new HashSet<>();
            //这里数据库表没有建roles字段，为了演示就直接写个admin角色了。（以后用在建表时把roles字段加上，然后取过来放在这里就能赋予角色了）
            set.add("admin");
            authorizationInfo.setRoles(set);
            // 返回给shiro
            return authorizationInfo;
        }*/
        return null;
    }
}
