package com.smilegate.digerapigateway.filter.authentication.jwt;

import com.smilegate.digerapigateway.filter.authentication.jwt.component.JwtPair;

public interface JwtAgent {

    JwtPair provide(Long userId, String loginId);

    void verify(String jwt);

    Long parseUserId(String jwt);
}
