package com.smilegate.digeruserservice.controller;

import com.smilegate.digeruserservice.controller.dto.request.JoinRequest;
import com.smilegate.digeruserservice.controller.dto.request.LoginRequest;
import com.smilegate.digeruserservice.controller.dto.response.UserResponse;
import com.smilegate.digeruserservice.controller.dto.response.UserTokenResponse;
import com.smilegate.digeruserservice.controller.usecase.UserApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserApplicationService userApplicationService;

    @GetMapping("{userId}")
    public ResponseEntity<UserResponse> loadInfo(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok().body(
                userApplicationService.retrieve(userId)
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
    public ResponseEntity<UserTokenResponse> login(
            @RequestBody LoginRequest loginRequest
    ) {
        return ResponseEntity.ok().body(
                userApplicationService.login(
                        loginRequest.loginId(),
                        loginRequest.password()
                )
        );
    }
}
