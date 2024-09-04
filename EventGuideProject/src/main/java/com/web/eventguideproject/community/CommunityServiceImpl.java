package com.web.eventguideproject.community;

import com.web.eventguideproject.member.Member;
import com.web.eventguideproject.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CommunityServiceImpl implements  CommunityService {

    @Autowired
    private CommunityRepository communityRepository; // 커뮤니티 정보 접근

    @Autowired
    private MemberRepository memberRepository; // 로그인한 회원 정보 접근

    @Override
    public CommunityDTO write(CommunityDTO communityDTO) {
        // 로그인한 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberSeq = (Long) authentication.getPrincipal();

        // seq 를 사용해 Member 엔티티 조회
        Member member = memberRepository.findById(memberSeq)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자입니다."));

        // DTO -> Entity 로 변환
        Community community = new Community();
        community.setTitle(communityDTO.getTitle());
        community.setContent(communityDTO.getContent());
        community.setUploadFile(communityDTO.getUploadFile());
        community.setMember(member); // 작성자 설정

        // Entity 저장
        communityRepository.save(community);

        // Entity -> DTO 변환
        communityDTO.setId(community.getId());
        communityDTO.setLikes(community.getLikes());
        communityDTO.setAuthorNickName(community.getAuthorNickName()); // 작성자의 닉네임으로 설정

        return communityDTO;
    }
}
