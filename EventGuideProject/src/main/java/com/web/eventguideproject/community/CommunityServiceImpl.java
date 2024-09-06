package com.web.eventguideproject.community;

import com.web.eventguideproject.auth.UserPrincipal;
import com.web.eventguideproject.member.Member;
import com.web.eventguideproject.member.MemberRepository;
import com.web.eventguideproject.uploadfile.UploadFile;
import com.web.eventguideproject.uploadfile.UploadFileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class CommunityServiceImpl implements  CommunityService {

    @Autowired
    private CommunityRepository communityRepository; // 커뮤니티 정보 접근

    @Autowired
    private MemberRepository memberRepository; // 로그인한 회원 정보 접근

    @Autowired
    private UploadFileServiceImpl uploadFileService; // 업로드한 파일 정보 접근

    @Override
    public CommunityDTO write(CommunityDTO communityDTO, MultipartFile uploadFile) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Principal 에서 UserPrincipal 을 추출합니다.

        if (!(authentication.getPrincipal() instanceof UserPrincipal)) {
            throw new IllegalArgumentException("유효하지 않은 사용자 정보입니다.");
        }
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Long memberSeq = userPrincipal.getSeq();

        // seq 를 사용해 Member 엔티티 조회
        Member member = memberRepository.findById(memberSeq)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자입니다."));

        // DTO -> Entity 로 변환
        Community community = new Community();
        community.setTitle(communityDTO.getTitle());
        community.setContent(communityDTO.getContent());
        community.setMember(member); // 작성자 설정

        // 파일 업로드 처리
        if (uploadFile != null && !uploadFile.isEmpty()) {
            UploadFile savedFile = uploadFileService.saveFile(uploadFile);
            community.setUploadFile(savedFile.getFilePath()); // 파일 경로를 저장
        }

        // Entity 저장
        communityRepository.save(community);

        // Entity -> DTO 변환
        communityDTO.setId(community.getId());
        communityDTO.setLikes(community.getLikes());
        communityDTO.setAuthorNickName(community.getAuthorNickName()); // 작성자의 닉네임으로 설정

        return communityDTO;
    }
}
