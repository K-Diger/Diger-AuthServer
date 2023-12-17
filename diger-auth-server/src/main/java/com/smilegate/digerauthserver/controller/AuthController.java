package com.smilegate.digerauthserver.controller;

import com.smilegate.digerauthserver.common.cookie.CookieAgent;
import com.smilegate.digerauthserver.common.jwt.component.JwtPair;
import com.smilegate.digerauthserver.controller.dto.request.JoinRequest;
import com.smilegate.digerauthserver.controller.dto.request.LoginRequest;
import com.smilegate.digerauthserver.controller.dto.response.UserResponse;
import com.smilegate.digerauthserver.controller.usecase.AuthApplicationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class AuthController {
    private final CookieAgent cookieAgent;
    private final AuthApplicationService authApplicationService;

    @PostMapping("/auth")
    public ResponseEntity<Long> auth(
            @RequestHeader("Authorization") String token
    ) {
        return ResponseEntity
                .ok()
                .body(authApplicationService.authToken(token));
    }

    @PostMapping("/join")
    public ResponseEntity<UserResponse> join(
            @RequestBody JoinRequest joinRequest
    ) {
        return ResponseEntity
                .ok()
                .body(authApplicationService.join(joinRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(
            @RequestBody LoginRequest loginRequest,
            HttpServletResponse httpServletResponse
    ) {
        JwtPair jwtPair = authApplicationService.login(loginRequest);

        httpServletResponse.addCookie(
                cookieAgent.createAccessToken(jwtPair.accessToken())
        );
        httpServletResponse.addCookie(
                cookieAgent.createRefreshToken(jwtPair.refreshToken())
        );

        return ResponseEntity
                .ok()
                .body(null);
    }
}
