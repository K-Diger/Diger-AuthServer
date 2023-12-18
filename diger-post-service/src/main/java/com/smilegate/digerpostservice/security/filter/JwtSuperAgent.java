package com.smilegate.digerpostservice.security.filter;

import com.smilegate.digerpostservice.feign.dto.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public interface JwtSuperAgent {
    UserResponse loadUserEntityByRequestWithFeign(HttpServletRequest httpServletRequest);
}
