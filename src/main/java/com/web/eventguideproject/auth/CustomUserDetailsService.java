package com.web.eventguideproject.auth;

import com.web.eventguideproject.member.Member;
import com.web.eventguideproject.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service // 이 클래스를 스프링의 서비스로 등록합니다.
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository; // MemberRepository를 주입받습니다.

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        // MemberId로 사용자를 찾습니다.
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() ->
                        new UsernameNotFoundException("사용자 아이디를 찾을 수 없습니다.: " + memberId)
                );

        return UserPrincipal.create(member); // 찾은 사용자를 UserPrincipal로 변환하여 반환합니다.
    }

    public UserDetails loadUserById(Long seq) {
        // seq로 사용자를 찾습니다.
        Member member = memberRepository.findById(seq).orElseThrow(
                () -> new UsernameNotFoundException("사용자를 찾을 수 없습니다.: " + seq)
        );

        return UserPrincipal.create(member); // 찾은 사용자를 UserPrincipal로 변환하여 반환합니다.
    }

}
