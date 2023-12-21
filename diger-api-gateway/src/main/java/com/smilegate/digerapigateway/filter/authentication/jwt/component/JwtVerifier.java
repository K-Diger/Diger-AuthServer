package com.smilegate.digerapigateway.filter.authentication.jwt.component;

import com.smilegate.digerapigateway.exception.BaseException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.smilegate.digerapigateway.exception.ExceptionType.E400;
import static com.smilegate.digerapigateway.exception.ExceptionType.E401;

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
            throw new BaseException(E400);
        } catch (ExpiredJwtException exception) {
            throw new BaseException(E401);
        }
    }
}
