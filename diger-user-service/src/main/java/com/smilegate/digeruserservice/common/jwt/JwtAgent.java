package com.smilegate.digeruserservice.common.jwt;

import com.smilegate.digeruserservice.common.jwt.component.JwtPair;

public interface JwtAgent {

    JwtPair provide(String loginId);

    void verify(String jwt);

    Long parseUserId(String jwt);
}
