package com.smilegate.digeruserservice.auth;

import com.smilegate.digeruserservice.auth.jwt.JwtPair;

public interface JwtAgent {

    JwtPair provide(String loginId);

    void verify(String jwt);

    Long parseUserId(String jwt);
}
