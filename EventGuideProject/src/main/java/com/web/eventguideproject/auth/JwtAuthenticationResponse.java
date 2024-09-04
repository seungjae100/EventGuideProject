package com.web.eventguideproject.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthenticationResponse {

    private String accessToken; // 클라이언트에게 발급할 JWT 액세스 토큰을 저장할 변수입니다.
    private String tokenType = "Bearer"; // 토큰의 유형을 나타내며 기본적으로 "Bearer"로 설정됩니다.

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken; // 생성자를 통해 액세스 토큰을 초기화합니다.
    }
}
