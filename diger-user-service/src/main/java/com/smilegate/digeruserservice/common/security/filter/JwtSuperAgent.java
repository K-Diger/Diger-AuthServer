package com.smilegate.digeruserservice.common.security.filter;

import com.smilegate.digeruserservice.common.exception.ExceptionType;
import com.smilegate.digeruserservice.common.exception.UserServerException;
import com.smilegate.digeruserservice.common.jwt.JwtAgent;
import com.smilegate.digeruserservice.domain.persistence.UserEntity;
import com.smilegate.digeruserservice.domain.persistence.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtSuperAgent {

    private final JwtAgent jwtAgent;
    private final UserRepository userRepository;

    private static final String HEADER_CONST = "Authorization";
    private static final int BEARER_PREFIX = 7;

    public UserEntity loadUserEntityByRequest(HttpServletRequest httpServletRequest) {
        String accessToken = httpServletRequest.getHeader(HEADER_CONST).substring(BEARER_PREFIX);
        Long userId = jwtAgent.parseUserId(accessToken);
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if (userEntity.isEmpty()) {
            throw new UserServerException(ExceptionType.E404);
        }
        return userEntity.get();
    }
}
