package com.smilegate.digeruserservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "AUTH-SERVICE")
public interface JwtAuthFeign {

    @PostMapping("/v1/auth")
    Long loadUserIdByToken(@RequestHeader("Authorization") String token);
}
