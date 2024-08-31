package com.web.eventguideproject.global;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  // 이 클래스가 스프링 설정 클래스임을 나타냅니다.
public class WebConfig implements WebMvcConfigurer {  // WebMvcConfigurer 인터페이스를 구현하여 MVC 설정을 구성합니다.

    @Override
    public void addCorsMappings(CorsRegistry registry) {  // CORS 매핑을 추가하는 메서드입니다.
        registry.addMapping("/**")  // 모든 경로에 대해 CORS를 허용합니다.
                .allowedOrigins("http://localhost:3000")  // 특정 오리진(React 앱)에서의 요청을 허용합니다.
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // 허용할 HTTP 메서드를 설정합니다.
                .allowedHeaders("*");  // 모든 헤더를 허용합니다. (클라이언트가 요청 시 어떤 헤더든지 서버로 보낼 수 있도록 허용하는 설정입니다.)
    }
}