package com.smilegate.digeruserservice.security.filter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "AUTH-SERVICE", url = "http://localhost:8080")
public interface JwtAuthFeign {

    @PostMapping("/v1/auth")
    Long loadUserIdByToken(@RequestHeader("Authorization") String token);
}
