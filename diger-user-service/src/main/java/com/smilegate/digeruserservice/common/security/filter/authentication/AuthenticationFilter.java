package com.smilegate.digeruserservice.common.security.filter.authentication;

import com.smilegate.digeruserservice.common.exception.ExceptionType;
import com.smilegate.digeruserservice.common.exception.UserServerException;
import com.smilegate.digeruserservice.common.jwt.JwtAgent;
import com.smilegate.digeruserservice.domain.UserVo;
import com.smilegate.digeruserservice.domain.persistence.UserEntity;
import com.smilegate.digeruserservice.domain.persistence.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Optional;

@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final JwtAgent jwtAgent;

    private static final String HEADER_CONST = "Authorization";
    private static final int BEARER_PREFIX = 7;
    private static final String SECURITY_CONTEXT_ROLE_PREFIX = "ROLE_";

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws AuthenticationException {
        if (isByPassURI(request.getRequestURI())) {
            return null;
        }

        UserVo userVo = loadUserEntityByRequest(request).toVo();
        String loginId = userVo.getLoginId();
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginId);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = createToken(userDetails);
        return getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
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

    private UserEntity loadUserEntityByRequest(HttpServletRequest httpServletRequest) {
        String accessToken = httpServletRequest.getHeader(HEADER_CONST).substring(BEARER_PREFIX);
        Long userId = jwtAgent.parseUserId(accessToken);
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if (userEntity.isEmpty()) {
            throw new UserServerException(ExceptionType.E404);
        }
        return userEntity.get();
    }

    private UsernamePasswordAuthenticationToken createToken(UserDetails userDetails) {
        return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );
    }
}
