package com.smilegate.digerpostservice.common.jwt;

import com.smilegate.digeruserservice.common.jwt.component.JwtPair;

public interface JwtAgent {

    JwtPair provide(Long userId, String loginId);

    void verify(String jwt);

    Long parseUserId(String jwt);
}
