package com.likc.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Instant;
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
    public String createJwt(Map<String, String> payloadClaims) {
        Instant now = Instant.now();
        return JWT.create()
                .withIssuer("likc")
                .withExpiresAt(now.plusMillis(expire * 1000))
                .withIssuedAt(now)
                .withPayload(payloadClaims)
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
    public DecodedJWT verify(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer("likc")
                    .build()
                    .verify(token);
        } catch (TokenExpiredException t) {
            log.error("JWT超时：{}", t.getMessage());
            throw new JWTVerificationException("JWT超时");
        } catch (Exception e) {
            log.error("JWT校验异常：{}", e.getMessage());
        }

        throw new JWTVerificationException("JWT校验异常");
    }

}
