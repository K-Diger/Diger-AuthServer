package com.smilegate.digeruserservice.common.security;

import com.smilegate.digeruserservice.common.jwt.JwtAgent;
import com.smilegate.digeruserservice.common.security.filter.authentication.AuthenticationFilter;
import com.smilegate.digeruserservice.common.security.filter.authorization.AuthorizationFilter;
import com.smilegate.digeruserservice.domain.persistence.UserRepository;
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

@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration {

    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final JwtAgent jwtAgent;

    private static final String[] NOT_NEED_AUTHORIZED = {
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
                        authorize.requestMatchers(NOT_NEED_AUTHORIZED).permitAll()
                )
                .authorizeHttpRequests((authorize) ->
                        authorize.anyRequest().hasAuthority("ROLE_USER")
                )
                // 토큰이 있나?
                .addFilterBefore(
                        new AuthenticationFilter(
                                userDetailsService,
                                userRepository,
                                jwtAgent
                        ),
                        UsernamePasswordAuthenticationFilter.class
                )
                // 토큰을 통해 조회한 ROLE이 적합한가?
                .addFilterAfter(
                        new AuthorizationFilter(
                                userRepository,
                                jwtAgent,
                                userDetailsService
                        ),
                        AuthenticationFilter.class
                );

        return http.build();
    }
}
