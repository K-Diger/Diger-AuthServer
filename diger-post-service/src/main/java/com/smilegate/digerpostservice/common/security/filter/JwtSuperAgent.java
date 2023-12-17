package com.smilegate.digerpostservice.common.security.filter;

import com.smilegate.digeruserservice.domain.persistence.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public interface JwtSuperAgent {
    UserEntity loadUserEntityByRequest(HttpServletRequest httpServletRequest);
}
