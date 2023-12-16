package com.smilegate.digeruserservice.common.security.filter.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smilegate.digeruserservice.common.exception.ExceptionType;
import com.smilegate.digeruserservice.common.exception.UserServerException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UserDetailsService userDetailsService;
    private final String[] permitURLs;

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws AuthenticationException {
        if (isByPassURI(request.getRequestURI())) return null;
        String loginId;
        try {
            AuthenticationRequestForm authenticationRequestForm = new ObjectMapper()
                    .readValue(
                            request.getInputStream(),
                            AuthenticationRequestForm.class
                    );

            loginId = authenticationRequestForm.loginId();
        } catch (IOException exception) {
            throw new UserServerException(ExceptionType.E500);
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginId);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = createToken(userDetails);
        return getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
    }

    private UsernamePasswordAuthenticationToken createToken(UserDetails userDetails) {
        return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAuthorities()
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
