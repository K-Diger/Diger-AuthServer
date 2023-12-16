package com.smilegate.digeruserservice.common.security.filter.authorization;

import com.smilegate.digeruserservice.common.exception.ExceptionType;
import com.smilegate.digeruserservice.common.exception.UserServerException;
import com.smilegate.digeruserservice.common.jwt.JwtAgent;
import com.smilegate.digeruserservice.domain.UserVo;
import com.smilegate.digeruserservice.domain.persistence.Role;
import com.smilegate.digeruserservice.domain.persistence.UserEntity;
import com.smilegate.digeruserservice.domain.persistence.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final JwtAgent jwtAgent;
    private final UserDetailsService userDetailsService;
    private final String[] permitURLs;

    private static final String HEADER_CONST = "Authorization";
    private static final int BEARER_PREFIX = 7;
    private static final String SECURITY_CONTEXT_ROLE_PREFIX = "ROLE_";
    private static final SimpleGrantedAuthority targetAuthority = new SimpleGrantedAuthority(
            SECURITY_CONTEXT_ROLE_PREFIX + Role.NOT_AUTH
    );

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        if (isByPassURI(request.getRequestURI())) filterChain.doFilter(request, response);
        else {
            UserVo userVo = loadUserEntityByRequest(request).toVo();
            Authentication authentication = getAuthentication(userVo.getLoginId());
            validateAuthenticationBySecurityHolder(authentication);

            filterChain.doFilter(request, response);
        }
    }

    private void validateAuthenticationBySecurityHolder(Authentication authentication) {
        if (authentication.getAuthorities().contains(targetAuthority)) {
            throw new UserServerException(ExceptionType.E401);
        }
    }

    public UserEntity loadUserEntityByRequest(HttpServletRequest httpServletRequest) {
        String accessToken = httpServletRequest.getHeader(HEADER_CONST).substring(BEARER_PREFIX);
        Long userId = jwtAgent.parseUserId(accessToken);
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if (userEntity.isEmpty()) {
            throw new UserServerException(ExceptionType.E404);
        }
        return userEntity.get();
    }

    private Authentication getAuthentication(String loginId) {
        final UserDetails user = userDetailsService.loadUserByUsername(loginId);
        return new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities()
        );
    }

    private Boolean isByPassURI(String URI) {
        for (String permitURL : permitURLs) {
            if (permitURL.equals(URI)) {
                return true;
            }
        }
        return false;
    }
}
