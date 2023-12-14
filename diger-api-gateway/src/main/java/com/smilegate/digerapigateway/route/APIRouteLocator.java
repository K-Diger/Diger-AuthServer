package com.smilegate.digerapigateway.route;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class APIRouteLocator {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/v1/user/**")
                        .uri("http://localhost:8081/")
                )
                .route("post-service", r -> r.path("/v1/post/**")
                        .uri("http://localhost:8082")
                )
                .build();
    }
}
