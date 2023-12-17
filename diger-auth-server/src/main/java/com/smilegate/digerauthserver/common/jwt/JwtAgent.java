package com.smilegate.digerauthserver.common.jwt;

import com.smilegate.digerauthserver.common.jwt.component.JwtPair;

public interface JwtAgent {

    JwtPair provide(Long userId, String loginId);

    void verify(String jwt);

    Long parseUserId(String jwt);
}
