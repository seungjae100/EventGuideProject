package com.web.eventguideproject.auth;

import com.web.eventguideproject.member.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager; // 인증 메니저를 주입받습니다.

    @Autowired
    private JwtTokenProvider jwtTokenProvider; // JWT 토큰 제공 클래스를 주입받습니다.

    @Autowired
    private MemberService memberService; // MemberService를 주입받습니다.

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser (@Valid @RequestBody LoginDTO loginDTO) {
        // 로그인 시도 시 인증을 수행합니다.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getMemberId(),
                        loginDTO.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication); // 인증 정보를 보안 컨텍스트에 저장합니다.

        String jwt = jwtTokenProvider.generateToken(authentication); // 인증 정보를 바탕으로 JWT 토큰을 생성합니다.
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt)); // 생성된 JWT 토큰을 응답으로 반환합니다.
    }



}
