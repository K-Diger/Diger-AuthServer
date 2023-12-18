package com.smilegate.digerpostservice.security.filter;

import com.smilegate.digerpostservice.common.exception.ExceptionType;
import com.smilegate.digerpostservice.common.exception.PostServerException;
import com.smilegate.digerpostservice.feign.JwtAuthFeign;
import com.smilegate.digerpostservice.feign.UserFeign;
import com.smilegate.digerpostservice.feign.dto.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtSuperAgentImpl implements JwtSuperAgent {

    private final JwtAuthFeign jwtAuthFeign;
    private final UserFeign userFeign;

    private static final String HEADER_CONST = "Authorization";

    @Override
    public UserResponse loadUserEntityByRequestWithFeign(HttpServletRequest httpServletRequest) {
        String accessToken = httpServletRequest.getHeader(HEADER_CONST);
        Long userId = jwtAuthFeign.loadUserIdByToken(accessToken);
        Optional<UserResponse> userResponse = Optional.ofNullable(
                userFeign.loadUserInfoById(accessToken, userId)
        );

        if (userResponse.isEmpty()) throw new PostServerException(ExceptionType.E401);

        return userResponse.get();
    }
}
