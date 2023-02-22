package com.example.backend.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author HuaRunSheng
 * @date 2022/10/12 15:53
 * @description :
 */
@Component
public class TokenUtil {
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 传入登录信息,生成token
     * @param details
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }
    public String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }
    private String generateJwt(Map<String, Object> map){
        return Jwts.builder()
                .setClaims(map)
                // 签名
                .signWith(SignatureAlgorithm.HS512, secret)
                // 过期时间
                .setExpiration(new Date(System.currentTimeMillis()+ expiration*1000))
                .compact();
    }

    /**
     * 获取token中的信息
     * @param token
     * @return
     */
    public Claims getTokenBody(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 根据token得到用户名
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token){
        return (String) this.getTokenBody(token).get("username");
    }

    /**
     * 根据token判断当前时间内,token是否过期
     * @param token
     * @return
     */
    public boolean isExpiration(String token){
        return this.getTokenBody(token).getExpiration().before(new Date());
    }

    /**
     * 刷新token
     * @param token
     * @return
     */
    public String refreshToken(String token){
        Claims claims=this.getTokenBody(token);
        claims.setExpiration(new Date());
        return this.generateJwt(claims);
    }

    /**
     * 判断token是否可以被刷新
     * @param token
     * @return
     */
    public boolean canRefresh(String token) {
        return !isExpiration(token);
    }
    /**
     * 验证token是否有效
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isExpiration(token);
    }
}
