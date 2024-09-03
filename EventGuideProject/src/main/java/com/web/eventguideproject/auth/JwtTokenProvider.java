package com.web.eventguideproject.auth;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component // 이 클래스를 스프링의 컴포넌트로 등록하여 Bean으로 사용합니다.
public class JwtTokenProvider {

    @Value("${jwt.secret}") // application.yml 파일에서 가져온 jwt.secret 값을 주입 받습니다.
    private String jwtSecret;

    @Value("${jwt.expirationMs}") // application.yml 파일에서 가져온 jwt.expirationMs 값을 주입 받습니다.
    private int jwtExpirationMs;

    public String generateToken(Authentication authentication) {
        // 인증 정보를 바탕으로 JWT 토큰을 생성합니다.
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getSeq())) // 토큰의 주제로 사용자의 seq를 설정합니다.
                .setIssuedAt(new Date()) // 토큰 발행 시간을 설정합니다.
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)) // 토큰 만료시간을 설정합니다.
                .signWith(SignatureAlgorithm.HS512, jwtSecret) // 서명 알고리즘과 비밀키를 설정합니다.
                .compact();
    }

    public Long getuserIdFromJWT(String token) {
        // JWT 토큰에서 사용자 ID(seq) 를 추출합니다.
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        // JWT 토큰의 유효성을 검사합니다.
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            // 유효하지 않은 JWT 토큰에 대한 예외처리
            return false;
        }
    }
}
