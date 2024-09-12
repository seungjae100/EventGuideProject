package com.web.eventguideproject.auth;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // 이 클래스를 스프링의 컴포넌트로 등록하여 Bean 으로 사용합니다.
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider; // JwtTokenProvider 를 주입받습니다.

    @Autowired
    private CustomUserDetailsService customUserDetailsService; // CustomUserDetailsService 를 주입받습니다.

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // JWT 토큰을 추출하여 유효성을 검사학고, 유효한 경우 인증을 설정합니다.
        try {
            String jwt = getJwtFromRequest(request); // 요청에서 JWT 토큰을 가져옴

            if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
                Long seq = jwtTokenProvider.getUserIdFromJWT(jwt);
                // 토큰에서 가져온 seq 로 사용자 정보를 로드
                UserDetails userDetails = customUserDetailsService.loadUserById(seq);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
                }
        } catch (Exception e){
            logger.error("보안 컨텐츠에서 사용자 인증을 설정 할 수 없습니다.", e);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        // HTTP 요청 헤더에서 JWT 토큰을 추출합니다.
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
