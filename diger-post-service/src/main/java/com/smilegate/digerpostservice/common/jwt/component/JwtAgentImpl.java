package com.smilegate.digerpostservice.common.jwt.component;

import com.smilegate.digeruserservice.common.jwt.JwtAgent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAgentImpl implements JwtAgent {

    private final JwtGenerator jwtGenerator;
    private final JwtVerifier jwtVerifier;
    private final JwtParser jwtParser;

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
        return jwtParser.getId(jwt);
    }
}
