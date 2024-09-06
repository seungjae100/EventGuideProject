package com.web.eventguideproject.auth;

import com.web.eventguideproject.member.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserPrincipal implements UserDetails {

    private Long seq;
    private String memberId;
    private String password;

    // Member 객체를 UserPrincipal 로 변환하는 메서드
    public static UserPrincipal create(Member member) {
        UserPrincipal userPrincipal = new UserPrincipal();
        userPrincipal.seq = member.getSeq();
        userPrincipal.memberId = member.getMemberId();
        userPrincipal.password = member.getPassword();

        // 디버깅을 위한 로그 추가
        System.out.println("UserPrincipal created with seq: " + userPrincipal.seq);

        return userPrincipal;

    }

    // UserDetails 인터페이스에서 요구하는 메서드들을 구현합니다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return memberId;
    }

    public Long getSeq() {
        return seq;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}




