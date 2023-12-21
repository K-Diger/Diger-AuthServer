package com.smilegate.digeruserservice.security.filter.authorization;

import com.smilegate.digeruserservice.common.exception.ExceptionType;
import com.smilegate.digeruserservice.common.exception.UserServerException;
import com.smilegate.digeruserservice.common.jwt.JwtAgent;
import com.smilegate.digeruserservice.domain.persistence.Role;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JwtAgent jwtAgent;

    private static final String SECURITY_CONTEXT_ROLE_PREFIX = "ROLE_";
    private static final SimpleGrantedAuthority targetAuthority =
            new SimpleGrantedAuthority(SECURITY_CONTEXT_ROLE_PREFIX + Role.NOT_AUTH);

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        if (isByPassURI(request.getRequestURI())) filterChain.doFilter(request, response);
        else {
            String loginId = jwtAgent.parseUserLoginId(request.getHeader("Authorization"));
            Authentication authentication = getAuthentication(loginId);
            validateAuthenticationBySecurityHolder(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        }
    }

    private void validateAuthenticationBySecurityHolder(Authentication authentication) {
        if (authentication.getAuthorities().contains(targetAuthority)) {
            throw new UserServerException(ExceptionType.E401);
        }
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
        String[] byPassURI = {"/v1/join", "/v1/login"};
        for (String uri : byPassURI) {
            if (uri.equals(URI)) {
                return true;
            }
        }
        return false;
    }
}
