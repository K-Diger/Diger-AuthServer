package com.smilegate.digerauthserver.controller;

import com.smilegate.digerauthserver.common.cookie.CookieAgent;
import com.smilegate.digerauthserver.controller.dto.request.JoinRequest;
import com.smilegate.digerauthserver.controller.dto.request.LoginRequest;
import com.smilegate.digerauthserver.controller.dto.response.AuthenticationResponse;
import com.smilegate.digerauthserver.controller.dto.response.UserResponse;
import com.smilegate.digerauthserver.controller.feignclient.UserFeignClient;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class AuthController {

    private final UserFeignClient userFeignClient;
    private final CookieAgent cookieAgent;

    @PostMapping("/join")
    public ResponseEntity<UserResponse> join(
            @RequestBody JoinRequest joinRequest
    ) {
        return ResponseEntity
                .ok()
                .body(userFeignClient.join(joinRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(
            @RequestBody LoginRequest loginRequest,
            HttpServletResponse httpServletResponse
    ) {
        AuthenticationResponse authenticationResponse = userFeignClient.login(loginRequest);

        httpServletResponse.addCookie(
                cookieAgent.createAccessToken(authenticationResponse.accessToken())
        );
        httpServletResponse.addCookie(
                cookieAgent.createRefreshToken(authenticationResponse.refreshToken())
        );

        return ResponseEntity
                .ok()
                .body(null);
    }
}
