package com.smilegate.digerauthserver.common.jwt.component;

import com.smilegate.digerauthserver.common.exception.AuthServerException;
import com.smilegate.digerauthserver.common.exception.ExceptionType;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtVerifier {

    private final JwtProperties jwtProperties;

    public void execute(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(jwtProperties.getSigningKey()).build()
                    .parseClaimsJws(token);
        } catch (MalformedJwtException | IllegalArgumentException ex) {
            throw new AuthServerException(ExceptionType.E400);
        } catch (ExpiredJwtException exception) {
            throw new AuthServerException(ExceptionType.E401_TOKEN_EXPIRED);
        }
    }
}
