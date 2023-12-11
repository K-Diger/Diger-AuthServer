package com.smilegate.digerapigateway.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAgentImpl implements JwtAgent {

    private final JwtGenerator jwtGenerator;
    private final JwtVerifier jwtVerifier;
    private final JwtParser jwtParser;

    @Override
    public JwtPair provide() {
        return jwtGenerator.execute(1L, "");
    }

    @Override
    public void verify(String jwt) {
        jwtVerifier.execute(jwt);
    }

    @Override
    public Long parseUserId(String jwt) {
        return jwtParser.getId(jwt);
    }
}
