package com.smilegate.digerpostservice.common.jwt.component;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtParser {

    private final JwtProperties jwtProperties;
    private final JwtVerifier jwtVerifier;

    public Long getId(String token) {
        jwtVerifier.execute(token);
        Object id = Jwts.parserBuilder()
                .setSigningKey(jwtProperties.getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody().get("id");
        return Long.valueOf(String.valueOf(id));
    }

    public String getLoginId(String token) {
        jwtVerifier.execute(token);
        Object loginId = Jwts.parserBuilder()
                .setSigningKey(jwtProperties.getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody().get("loginId");
        return String.valueOf(loginId);
    }
}