package com.smilegate.digerpostservice.feign.user;

import com.smilegate.digerpostservice.feign.user.dto.request.UserUpdatePointRequest;
import com.smilegate.digerpostservice.feign.user.dto.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service")
public interface UserFeign {

    @PutMapping("/v1/point")
    UserResponse updateUserPoint(
            @RequestHeader("Authorization") String token,
            @RequestBody UserUpdatePointRequest updatePointRequest
    );

    @GetMapping("/v1/")
    UserResponse loadMyInfo(
            @RequestHeader("Authorization") String token
    );
}
