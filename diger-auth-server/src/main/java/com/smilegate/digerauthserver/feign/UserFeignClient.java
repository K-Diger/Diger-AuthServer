package com.smilegate.digerauthserver.feign;

import com.smilegate.digerauthserver.controller.dto.request.JoinRequest;
import com.smilegate.digerauthserver.controller.dto.request.LoginRequest;
import com.smilegate.digerauthserver.controller.dto.response.UserResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service")
public interface UserFeignClient {

    @PostMapping("/v1/join")
    UserResponse join(@RequestBody @Valid JoinRequest joinRequest);

    @PostMapping("/v1/login")
    UserResponse login(@RequestBody @Valid LoginRequest loginRequest);
}
