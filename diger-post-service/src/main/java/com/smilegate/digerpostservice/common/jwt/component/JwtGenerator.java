package com.smilegate.digerpostservice.common.jwt.component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtGenerator {

    private final JwtProperties jwtProperties;
    private static final Long ACCESS_TOKEN_EXPIRE_TIME = 30 * 60 * 1000L;
    private static final Long REFRESH_TOKEN_EXPIRE_TIME = 270 * 24 * 60 * 60 * 1000L;

    public JwtPair execute(Long userId, String loginId) {
        return new JwtPair(buildAccessToken(userId, loginId), buildRefreshToken());
    }

    private String buildAccessToken(Long userId, String loginId) {
        return Jwts.builder()
                .signWith(jwtProperties.getSigningKey())
                .setHeaderParam("type", "JWT")
                .setClaims(buildClaims(userId, loginId))
                .setExpiration(buildExpiredAt(ACCESS_TOKEN_EXPIRE_TIME))
                .compact();
    }

    private String buildRefreshToken() {
        return Jwts.builder()
                .signWith(jwtProperties.getSigningKey())
                .setHeaderParam("type", "JWT")
                .setExpiration(buildExpiredAt(REFRESH_TOKEN_EXPIRE_TIME))
                .compact();
    }

    private Claims buildClaims(Long userId, String loginId) {
        Claims claims = Jwts.claims();
        claims.setSubject("jwt");
        claims.put("id", userId);
        claims.put("loginId", loginId);
        return claims;
    }

    private Date buildExpiredAt(Long expiredType) {
        return new Date(new Date().getTime() + expiredType);
    }
}
