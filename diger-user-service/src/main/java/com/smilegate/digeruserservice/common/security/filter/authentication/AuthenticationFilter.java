package com.smilegate.digeruserservice.common.security.filter.authentication;

import com.smilegate.digeruserservice.common.security.filter.JwtSuperAgent;
import com.smilegate.digeruserservice.domain.UserVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UserDetailsService userDetailsService;
    private final JwtSuperAgent jwtSuperAgent;

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws AuthenticationException {
        if (isByPassURI(request.getRequestURI())) return null;

        UserVo userVo = jwtSuperAgent.loadUserEntityByRequest(request).toVo();
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

    private UsernamePasswordAuthenticationToken createToken(UserDetails userDetails) {
        return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );
    }
}
