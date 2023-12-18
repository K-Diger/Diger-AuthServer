package com.smilegate.digeruserservice.security.filter;

import com.smilegate.digeruserservice.common.exception.ExceptionType;
import com.smilegate.digeruserservice.common.exception.UserServerException;
import com.smilegate.digeruserservice.domain.persistence.UserEntity;
import com.smilegate.digeruserservice.domain.persistence.UserRepository;
import com.smilegate.digeruserservice.feign.JwtAuthFeign;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtSuperAgentImpl implements JwtSuperAgent {

    private final UserRepository userRepository;
    private final JwtAuthFeign jwtAuthFeign;

    private static final String HEADER_CONST = "Authorization";

    @Override
    public UserEntity loadUserEntityByRequest(HttpServletRequest httpServletRequest) {
        String accessToken = httpServletRequest.getHeader(HEADER_CONST);
        Long userId = jwtAuthFeign.loadUserIdByToken(accessToken);
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if (userEntity.isEmpty()) {
            throw new UserServerException(ExceptionType.E404);
        }
        return userEntity.get();
    }
}
