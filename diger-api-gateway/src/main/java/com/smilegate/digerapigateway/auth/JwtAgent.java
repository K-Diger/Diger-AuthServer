package com.smilegate.digerapigateway.auth;

public interface JwtAgent {

    JwtPair provide();

    void verify(String jwt);

    Long parseUserId(String jwt);
}
