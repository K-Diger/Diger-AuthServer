package com.smilegate.digerpostservice.common.jwt.component;

import com.smilegate.digerpostservice.common.exception.PostServerException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.smilegate.digerpostservice.common.exception.ExceptionType.E400;
import static com.smilegate.digerpostservice.common.exception.ExceptionType.E401;


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
            throw new PostServerException(E400);
        } catch (ExpiredJwtException exception) {
            throw new PostServerException(E401);
        }
    }
}
