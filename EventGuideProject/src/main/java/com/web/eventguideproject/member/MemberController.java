package com.web.eventguideproject.member;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 이 클래스가 RESTful 웹 서비스의 컨트롤러임을 나타냅니다.
@RequestMapping("/api/members") // 이 컨트롤러의 기본 URL 경로를 설정합니다.
public class MemberController {

    @Autowired // MemberService 객체를 자동으로 주입받습니다.
    private MemberService memberService;

    @PostMapping("/register") // HTTP POST 요청을 처리하며 "/register" 경로로 매핑합니다.
    public ResponseEntity<String> register(@Valid @RequestBody MemberDTO memberDTO) { // 클라이언트로부터 Member 객체를 받아서 회원가입을 처리하는 메서드입니다.
        memberService.register(memberDTO); // 회원가입 로직 호출
        return ResponseEntity.ok("회원가입 성공");
    }
}
