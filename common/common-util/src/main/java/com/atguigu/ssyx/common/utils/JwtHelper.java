package com.atguigu.ssyx.common.utils;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import java.util.Date;

/**
 * token生成
 */
@Slf4j
public class JwtHelper {

    private static long tokenExpiration = 365*24*60*60*1000;
    private static String tokenSignKey = "ssyx";

    //根据userId+userName生成token字符串
    public static String createToken(Long userId, String userName) {
        return Jwts.builder()
                .setSubject("yx-USER")

                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))

                .claim("userId", userId)
                .claim("userName", userName)

                .signWith(SignatureAlgorithm.HS512, tokenSignKey)

                .compressWith(CompressionCodecs.GZIP)
                .compact();
    }

    public static Long getUserId(String token) {
        if(StringUtils.isEmpty(token)) return null;

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        Integer userId = (Integer)claims.get("userId");
        return userId.longValue();
        // return 1L;
    }

    public static String getUserName(String token) {
        if(StringUtils.isEmpty(token)) return "";

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (String)claims.get("userName");
    }

    public static void removeToken(String token) {
        //jwttoken无需删除，客户端扔掉即可。
    }

    /**
     * 生成token测试
     */
    public static void main(String[] args) {
        String token = JwtHelper.createToken(1L, "admin");
        log.info(token);

        log.info("UserId:{}", JwtHelper.getUserId(token));
        log.info("UserName:{}", JwtHelper.getUserName(token));
    }
}
