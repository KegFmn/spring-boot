package com.likc.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: likc
 * @Date: 2022/02/19/20:36
 * @Description: jwt工具类
 */
@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtUtils {

    /**
     *  密钥
     */
    private String secret;

    /**
     *  过期时间
     */
    private long expire;

    /**
     * 根据payload信息生成JSON WEB TOKEN
     *
     * @param payloadClaims 在jwt中存储的一些非隐私信息
     * @return
     */
    public String generateToken(HashMap<String, String> payloadClaims) {
        long currentTimeMillis = System.currentTimeMillis();
        Date expireTime = new Date(System.currentTimeMillis() + expire * 1000 * 60);
        return JWT.create()
                .withPayload(payloadClaims)
                .withExpiresAt(expireTime)
                .withIssuedAt(new Date(currentTimeMillis))
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * 校验并获得Token中的信息
     * 1、token的header和payload是否没改过；
     * 2、没有过期
     *
     * @param token
     * @return
     */
    public Map<String, Claim> getClaimByToken(String token) {
        try {
            return JWT
                    .require(Algorithm.HMAC256(secret))
                    .build()
                    .verify(token)
                    .getClaims();
        } catch (Exception e) {
            log.error("解析token出错", e);
            return null;
        }
    }

    /**
     * token是否过期
     * @return  true：过期
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }
}
