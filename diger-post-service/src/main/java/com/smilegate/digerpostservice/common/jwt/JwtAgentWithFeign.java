package com.smilegate.digerpostservice.common.jwt;

import com.smilegate.digerpostservice.common.jwt.component.JwtAgentImpl;
import com.smilegate.digerpostservice.common.jwt.component.JwtGenerator;
import com.smilegate.digerpostservice.common.jwt.component.JwtParser;
import com.smilegate.digerpostservice.common.jwt.component.JwtVerifier;
import com.smilegate.digerpostservice.feign.user.UserFeign;
import com.smilegate.digerpostservice.feign.user.dto.response.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class JwtAgentWithFeign extends JwtAgentImpl {

    private final UserFeign userFeign;


    public JwtAgentWithFeign(
            JwtGenerator jwtGenerator,
            JwtVerifier jwtVerifier,
            JwtParser jwtParser,
            UserFeign userFeign
    ) {
        super(jwtGenerator, jwtVerifier, jwtParser);
        this.userFeign = userFeign;
    }

    public UserResponse loadUserEntityByRequestWithFeign(
            HttpServletRequest request
    ) {
        return userFeign.loadMyInfo(request.getHeader("Authorization"));
    }
}
