package com.smilegate.digeruserservice.security;

import com.smilegate.digeruserservice.common.jwt.JwtAgent;
import com.smilegate.digeruserservice.security.filter.authentication.AuthenticationFilter;
import com.smilegate.digeruserservice.security.filter.authorization.AuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserDetailsService userDetailsService;
    private final JwtAgent jwtAgent;

    private static final String[] WHITE_LIST_URI = {
            "/v1/join",
            "/v1/login",
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(withDefaults())
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers(WHITE_LIST_URI).permitAll()
                )
                .authorizeHttpRequests((authorize) ->
                        authorize.anyRequest().hasAuthority("ROLE_USER")
                )
                .addFilterBefore(
                        new AuthenticationFilter(
                                userDetailsService,
                                jwtAgent
                        ),
                        UsernamePasswordAuthenticationFilter.class
                )
                .addFilterAfter(
                        new AuthorizationFilter(
                                userDetailsService,
                                jwtAgent
                        ),
                        AuthenticationFilter.class
                );
        return http.build();
    }
}
