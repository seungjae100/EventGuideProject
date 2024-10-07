package com.web.eventguideproject.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

// 인증되지 않은 사용자가 보호딘 리소스에 접근할 때 401 에러를 반환하는 역할을 합니다.
@Component // 이 클래스를 스프링의 컴포넌트로 등록하여 Bean으로 사용합니다.
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException { // 인증 예외 발생 시 호출됩니다.
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage()); // 401 에러를 반환하고, 예외 메시지를 함께 전달합니다.

    }
}