package com.smilegate.digerapigateway.auth;

import com.smilegate.digerapigateway.auth.core.JwtPair;

public interface JwtAgent {

    JwtPair provide(String loginId);

    void verify(String jwt);

    Long parseUserId(String jwt);
}
