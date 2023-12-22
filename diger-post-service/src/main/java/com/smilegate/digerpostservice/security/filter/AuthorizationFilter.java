package com.smilegate.digerpostservice.security.filter;

import com.smilegate.digerpostservice.common.exception.ExceptionType;
import com.smilegate.digerpostservice.common.exception.PostServerException;
import com.smilegate.digerpostservice.common.jwt.JwtAgentWithFeign;
import com.smilegate.digerpostservice.feign.user.dto.UserVo;
import com.smilegate.digerpostservice.feign.user.dto.response.UserResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {

    private final JwtAgentWithFeign jwtAgent;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        UserResponse userResponse = jwtAgent.loadUserEntityByRequestWithFeign(request);
        validateWriteAuthority(userResponse);
        validateIsUserAdminForWriteNotice(request, userResponse);

        UserVo userVo = new UserVo().fromUserResponse(userResponse);

        Authentication authentication = getAuthentication(userVo);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    private Authentication getAuthentication(UserVo userVo) {
        return new UsernamePasswordAuthenticationToken(
                userVo.getLoginId(),
                userVo.getPassword(),
                userVo.getAuthorities()
        );
    }

    private void validateWriteAuthority(UserResponse userResponse) {
        if (userResponse.role().equals("NOT_AUTH")) {
            throw new PostServerException(ExceptionType.E401);
        }
    }

    private void validateIsUserAdminForWriteNotice(
            HttpServletRequest request,
            UserResponse userResponse
    ) {
        if (request.getRequestURI().equals("/v1/notice")) {
            if (!userResponse.role().equals("ADMIN")) {
                throw new PostServerException(ExceptionType.E401);
            }
        }
    }
}
