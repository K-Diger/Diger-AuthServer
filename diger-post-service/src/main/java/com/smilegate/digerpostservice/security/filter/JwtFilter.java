package com.smilegate.digerpostservice.security.filter;

import com.smilegate.digerpostservice.common.exception.ExceptionType;
import com.smilegate.digerpostservice.common.exception.PostServerException;
import com.smilegate.digerpostservice.feign.dto.UserResponse;
import com.smilegate.digerpostservice.security.userdetails.UserVo;
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

/**
 * Securityholer에 등록하는 로직 추가해야함
 */

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtSuperAgent jwtSuperAgent;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        UserResponse userResponse = jwtSuperAgent.loadUserEntityByRequestWithFeign(request);
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
