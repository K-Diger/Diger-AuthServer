//package com.smilegate.digerauthserver.common.security;
//
//import com.smilegate.digerauthserver.common.config.AdditionalBeans;
//import com.smilegate.digerauthserver.domain.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.ObjectPostProcessor;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
//import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SpringSecurity {
//
//    private final UserDetailsService userDetailsService;
//    private final AdditionalBeans additionalBeans;
//    private final UserService userService;
//    private final ObjectPostProcessor<Object> objectPostProcessor;
//
//    private static final String[] WHITE_LIST_URI = {
//            "/actuator/**",
//            "/swagger-ui/**",
//            "/api-docs/swagger-config",
//            "/v1/join",
//            "/**"
//    };
//
//    @Bean
//    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .httpBasic(HttpBasicConfigurer::disable)
//                .csrf(CsrfConfigurer::disable)
//                .cors(Customizer.withDefaults())
//                .sessionManagement(configurer -> configurer
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers(WHITE_LIST_URI).permitAll()
//                        .anyRequest().authenticated()
//                )
//                .addFilterBefore(
//                        getAuthenticationFilter(),
//                        UsernamePasswordAuthenticationFilter.class
//                )
//        ;
//        return http.build();
//    }
//
//    @Bean
//    public AuthenticationManager getAuthenticationManager(
//            AuthenticationManagerBuilder auth
//    ) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(additionalBeans.bCryptPasswordEncoder());
//        return auth.build();
//    }
//
//    @Bean
//    protected AuthenticationFilter getAuthenticationFilter() throws Exception {
//        AuthenticationFilter authenticationFilter = new AuthenticationFilter(userService);
//        AuthenticationManagerBuilder builder = new AuthenticationManagerBuilder(objectPostProcessor);
//        authenticationFilter.setFilterProcessesUrl("/v1/login");
//        authenticationFilter.setAuthenticationManager(getAuthenticationManager(builder));
//        return authenticationFilter;
//    }
//}
