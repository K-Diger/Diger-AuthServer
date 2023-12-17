package com.smilegate.digerauthserver.controller.usecase;

import com.smilegate.digerauthserver.common.jwt.JwtAgent;
import com.smilegate.digerauthserver.common.jwt.component.JwtPair;
import com.smilegate.digerauthserver.controller.dto.request.JoinRequest;
import com.smilegate.digerauthserver.controller.dto.request.LoginRequest;
import com.smilegate.digerauthserver.controller.dto.response.UserResponse;
import com.smilegate.digerauthserver.controller.feignclient.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthApplicationService {

    private final JwtAgent jwtAgent;
    private final UserFeignClient userFeignClient;

    public Long authToken(String tokenPayload) {
        return jwtAgent.parseUserId(tokenPayload);
    }

    public UserResponse join(JoinRequest joinRequest) {
        return userFeignClient.join(joinRequest);
    }

    public JwtPair login(LoginRequest loginRequest) {
        UserResponse userResponse = userFeignClient.login(loginRequest);
        return jwtAgent.provide(userResponse.id(), userResponse.loginId());
    }
}
