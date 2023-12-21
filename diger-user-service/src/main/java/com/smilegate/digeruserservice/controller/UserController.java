package com.smilegate.digeruserservice.controller;

import com.smilegate.digeruserservice.common.cookie.CookieAgent;
import com.smilegate.digeruserservice.common.jwt.component.JwtPair;
import com.smilegate.digeruserservice.controller.dto.request.JoinRequest;
import com.smilegate.digeruserservice.controller.dto.request.LoginRequest;
import com.smilegate.digeruserservice.controller.dto.request.UpdatePointRequest;
import com.smilegate.digeruserservice.controller.dto.response.UserResponse;
import com.smilegate.digeruserservice.controller.usecase.UserApplicationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserApplicationService userApplicationService;
    private final CookieAgent cookieAgent;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> loadInfo(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok().body(
                userApplicationService.retrieve(userId)
        );
    }

    @GetMapping("/")
    public ResponseEntity<UserResponse> loadMyInfo() {
        return ResponseEntity.ok().body(
                userApplicationService.retrieveByLoginId(
                        String.valueOf(
                                SecurityContextHolder
                                        .getContext()
                                        .getAuthentication()
                                        .getPrincipal()
                        )
                )
        );
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/join")
    public ResponseEntity<UserResponse> join(
            @RequestBody JoinRequest joinRequest
    ) {
        return ResponseEntity.ok().body(
                userApplicationService.create(
                        joinRequest.loginId(),
                        joinRequest.password(),
                        joinRequest.nickname()
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(
            @RequestBody LoginRequest loginRequest,
            HttpServletResponse httpServletResponse
    ) {
        JwtPair jwtPair = userApplicationService.login(
                loginRequest.loginId(),
                loginRequest.password()
        );

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

    @PutMapping("/point")
    public ResponseEntity<UserResponse> updateUserPoint(
            @RequestBody UpdatePointRequest updatePointRequest
    ) {
        return ResponseEntity.ok().body(
                userApplicationService.updateUserPoint(
                        updatePointRequest.targetUserId(),
                        updatePointRequest.point()
                )
        );
    }
}
