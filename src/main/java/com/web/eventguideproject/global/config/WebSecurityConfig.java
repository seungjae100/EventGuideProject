package com.web.eventguideproject.global.config;

import com.web.eventguideproject.auth.CustomUserDetailsService;
import com.web.eventguideproject.auth.JwtAuthenticationEntryPoint;
import com.web.eventguideproject.auth.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // 이 클래스가 Spring 설정 클래스임을 나타냅니다.
@EnableWebSecurity // Spring Security를 활성화합니다.
public class WebSecurityConfig {

    @Autowired // 의존성을 주입받아 CustomUserDetailsService를 사용합니다.
    private CustomUserDetailsService customUserDetailsService;

    @Autowired // 의존성을 주입받아 JwtAuthenticationEntryPoint를 사용합니다.
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired // 의존성을 주입받아 JwtAuthenticationFilter를 사용합니다.
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean // SecurityFilterChain을 빈으로 등록하여 Spring Security 설정을 구성합니다.
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable() // CORS 설정을 활성화하고 CSRF 보호를 비활성화합니다.
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and() // 인증되지 않은 접근에 대한 처리를 설정합니다.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() // 세션을 사용하지 않도록 Stateless로 설정합니다.
                .authorizeHttpRequests() // HTTP 요청에 대한 인가를 설정합니다.
                .requestMatchers("/**").permitAll() // 모든 경로에 대해 인증 없이 접근을 허용합니다.
                .anyRequest().permitAll(); // 나머지 모든 요청도 인증 없이 접근을 허용합니다.

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // JWT 필터를 UsernamePasswordAuthenticationFilter 앞에 추가합니다.
        return http.build(); // 설정된 SecurityFilterChain을 반환합니다.
    }

    @Bean // AuthenticationManager를 빈으로 등록합니다.
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager(); // AuthenticationManager를 설정하여 반환합니다.
    }

    @Bean // PasswordEncoder를 빈으로 등록합니다.
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCrypt 암호화 방식을 사용하는 PasswordEncoder를 반환합니다.
    }
}