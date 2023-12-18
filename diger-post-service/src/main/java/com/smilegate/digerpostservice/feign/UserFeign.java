package com.smilegate.digerpostservice.feign;

import com.smilegate.digerpostservice.feign.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service")
public interface UserFeign {

    @GetMapping("/v1/{userId}")
    UserResponse loadUserInfoById(
            @RequestHeader("Authorization") String token,
            @PathVariable Long userId
    );
}
