package com.smilegate.digeruserservice.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {

    private static final String API_GATEWAY = "https:localhost:8000";
    private static final String AUTH_SERVER = "https:localhost:8080";
    private static final String USER_SERVER = "https:localhost:8081";
    private static final String POST_SERVER = "https:localhost:8082";

    private final List<String> allowedMethods = List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH");

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", defaultCorsConfig());
        return source;
    }

    private CorsConfiguration defaultCorsConfig() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(
                List.of(API_GATEWAY, AUTH_SERVER, USER_SERVER, POST_SERVER)
        );
        configuration.setAllowedMethods(allowedMethods);
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        return configuration;
    }
}
