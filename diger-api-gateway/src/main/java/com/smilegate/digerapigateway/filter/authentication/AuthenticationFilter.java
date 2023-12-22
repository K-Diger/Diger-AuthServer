package com.smilegate.digerapigateway.filter.authentication;

import com.smilegate.digerapigateway.exception.BaseException;
import com.smilegate.digerapigateway.exception.ExceptionType;
import com.smilegate.digerapigateway.filter.authentication.jwt.JwtAgent;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final JwtAgent jwtAgent;

    public AuthenticationFilter(JwtAgent jwtAgent) {
        super(Config.class);
        this.jwtAgent = jwtAgent;
    }

    public static class Config {
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            if (isURIWhiteList(request)) {
                return chain.filter(exchange);
            }
            validateAuthorizationHeader(request);

            return chain.filter(exchange);
        };
    }

    private Boolean isURIWhiteList(
            ServerHttpRequest requestUri
    ) {
        if (isWhiteListUserServiceURI(requestUri)) {
            return requestUri.getMethod().matches("POST");
        } else if (isWhiteListPostServiceURI(requestUri)) {
            return requestUri.getMethod().matches("GET");
        }

        return false;
    }

    private boolean isWhiteListPostServiceURI(ServerHttpRequest requestUri) {
        return requestUri.getURI().getPath().equals("/post-service/v1/all");
    }

    private boolean isWhiteListUserServiceURI(ServerHttpRequest requestUri) {
        return requestUri.getURI().getPath().equals("/user-service/v1/join") || requestUri.getURI().getPath().equals("/user-service/v1/login");
    }

    private void validateAuthorizationHeader(ServerHttpRequest request) {
        Optional<List<String>> authorization = Optional.ofNullable(
                request.getHeaders().get("Authorization")
        );
        if (authorization.isEmpty()) {
            throw new BaseException(ExceptionType.E400);
        }

        String[] splitedByBearer = authorization.get().get(0).split("Bearer ");
        if (splitedByBearer.length != 2) {
            throw new BaseException(ExceptionType.E400);
        }
        jwtAgent.verify(splitedByBearer[1]);
    }
}
