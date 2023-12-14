package com.smilegate.digeruserservice.controller;

import com.smilegate.digeruserservice.controller.dto.request.JoinRequest;
import com.smilegate.digeruserservice.controller.dto.request.LoginRequest;
import com.smilegate.digeruserservice.controller.dto.response.UserResponse;
import com.smilegate.digeruserservice.controller.dto.response.UserTokenResponse;
import com.smilegate.digeruserservice.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    @Value("${server.port}")
    private String port;

    private final UserService userService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/join")
    public ResponseEntity<UserResponse> join(
            @RequestBody JoinRequest joinRequest
    ) {
        return ResponseEntity.ok().body(
                userService.create(
                        joinRequest.loginId(),
                        joinRequest.password(),
                        joinRequest.nickname()
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<UserTokenResponse> login(
            @RequestBody LoginRequest loginRequest
    ) {
        return ResponseEntity.ok().body(
                userService.login(
                        loginRequest.loginId(),
                        loginRequest.password())
        );
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserResponse> loadInfo(
            @PathVariable Long userId
    ) {
        System.out.println("port = " + port);
        return ResponseEntity.ok().body(
                userService.loadUserInfo(userId)
        );
    }
}
