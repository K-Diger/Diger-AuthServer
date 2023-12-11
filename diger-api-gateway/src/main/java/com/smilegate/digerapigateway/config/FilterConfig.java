package com.smilegate.digerapigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        // r이라는 값이 들어오면
        // 경로를 확인한 후
        // 필터를 적용해서
        // URI로 이동시킨다.
        return routeLocatorBuilder.routes()
                .route(r -> r
                        .path("/v1/user/**")
//                        .filters(f -> f
//                                .addRequestHeader("user-service-request", "user-service-request-header")
//                                .addResponseHeader("user-service-response", "user-service-response-header")
//                        )
                        .uri("http://localhost:8081"))

                .route(r -> r
                        .path("/v1/post/**")
//                        .filters(f -> f
//                                .addRequestHeader("post-service-request", "post-service-request-header")
//                                .addResponseHeader("post-service-response", "post-service-response-header")
//                        )
                        .uri("http://localhost:8082"))
                .build();
    }
}
