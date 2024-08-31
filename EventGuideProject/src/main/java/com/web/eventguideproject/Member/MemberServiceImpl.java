package com.web.eventguideproject.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // 이 클래스가 서비스 구현체임을 나타내고, 스프링에서 빈으로 관리되도록 합니다.
public class MemberServiceImpl implements MemberService {

    @Autowired // MemberRepository 객체를 자동으로 주입받습니다.
    private MemberRepository memberRepository;

    @Override // 인터페이스에서 정의된 메서드를 구현합니다.
    public MemberDTO register(MemberDTO memberDTO) { // 회원가입 로직을 처리하는 메서드입니다.
        // DTO -> Entity 로 변환
         Member member = new Member();
         member.setMemberId(memberDTO.getMemberId());
         member.setPassword(memberDTO.getPassword());
         member.setEmail(memberDTO.getEmail());
         member.setMemberName(memberDTO.getMemberName());
         member.setNickName(memberDTO.getNickName());
         member.setBirth(memberDTO.getBirth());
         member.setSex(memberDTO.getSex());
         member.setPhone(memberDTO.getPhone());
         member.setAddress(memberDTO.getAddress());
         member.setRole("ROLE_MEMBER");

         // Entity 저장
         memberRepository.save(member);
         // Entity -> DTO 변환
         memberDTO.setSeq(member.getSeq());
         return memberDTO;
    }
}
