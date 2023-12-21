package com.smilegate.digerapigateway.filter.authentication.jwt.component;

import com.smilegate.digerapigateway.filter.authentication.jwt.JwtAgent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAgentImpl implements JwtAgent {

    private final JwtGenerator jwtGenerator;
    private final JwtVerifier jwtVerifier;
    private final JwtParser jwtParser;
    private static final int BEARER_PREFIX = 7;

    @Override
    public JwtPair provide(Long userId, String loginId) {
        return jwtGenerator.execute(userId, loginId);
    }

    @Override
    public void verify(String jwt) {
        jwtVerifier.execute(jwt);
    }

    @Override
    public Long parseUserId(String jwt) {
        jwt = jwt.substring(BEARER_PREFIX);
        jwtVerifier.execute(jwt);
        return jwtParser.getId(jwt);
    }
}
