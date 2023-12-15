//package com.smilegate.digerauthserver.common.security;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.smilegate.digerauthserver.common.security.request.LoginRequest;
//import com.smilegate.digerauthserver.domain.service.UserService;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//@RequiredArgsConstructor
//public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    private final UserService userService;
//
//    @Override
//    public Authentication attemptAuthentication(
//            HttpServletRequest request,
//            HttpServletResponse response
//    ) throws AuthenticationException {
//        try {
//            if (isLoginRequest(request)) {
//                // 로그인 요청을 읽어들인다. (POST 요청의 Body는 InputStream으로 읽어야한다.)
//                LoginRequest loginRequest = new ObjectMapper().readValue(
//                        request.getInputStream(),
//                        LoginRequest.class
//                );
//            /*
//                Spring Security가 관리할 인증 정보로 변환한 Token을 생성한다.
//                Id, Password, 권한이 들어간다.
//            */
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                        new UsernamePasswordAuthenticationToken(
//                                loginRequest.loginId(),
//                                loginRequest.password(),
//                                new ArrayList<>()
//                        );
//
//            /*
//                인증 매니저에게 생성한 토큰을 관리하도록
//            */
//                return getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
//    }
//
//    private boolean isLoginRequest(HttpServletRequest request) {
//        return request.getMethod().equals("POST") && request.getRequestURI().equals("/v1/login");
//    }
//
//    @Override
//    protected void successfulAuthentication(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain chain,
//            Authentication authResult
//    ) {
////        authResult.getPrincipal().;
////        String loginId = principal.getName();
////        System.out.println("loginId = " + loginId);
////        UserVo userVo = userService.loadByLoginId(loginId);
//
//    }
//}
