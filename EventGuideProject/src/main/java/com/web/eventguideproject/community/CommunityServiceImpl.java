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
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommunityServiceImpl implements  CommunityService {

    @Autowired
    private CommunityRepository communityRepository; // 커뮤니티 정보 접근

    @Autowired
    private MemberRepository memberRepository; // 로그인한 회원 정보 접근

    @Autowired
    private UploadFileServiceImpl uploadFileService; // 업로드한 파일 정보 접근

    @Autowired
    private CommunityMapper communityMapper; // Mapper 접근

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

        // Mapper를 사용하여 DTO -> Entity 변환
        Community community = communityMapper.toEntity(communityDTO);
        community.setMember(member); // 작성자 정보 설정
        /*
        Community community = new Community();
        community.setTitle(communityDTO.getTitle());
        community.setContent(communityDTO.getContent());
        community.setMember(member); // 작성자 설정
         */

        // 파일 업로드 처리
        if (uploadFile != null && !uploadFile.isEmpty()) {
            UploadFile savedFile = uploadFileService.saveFile(uploadFile);
            community.setUploadFile(savedFile.getFilePath()); // 파일 경로를 저장
        }

        // Entity 저장
        communityRepository.save(community);

        /* Entity -> DTO 변환
        communityDTO.setId(community.getId());
        communityDTO.setLikes(community.getLikes());
        communityDTO.setAuthorNickName(community.getAuthorNickName()); // 작성자의 닉네임으로 설정
        */
        //  Mapper를 사용하여 Entity -> DTO 변환
        return communityMapper.toDTO(community);
    }

    @Override
    public List<CommunityDTO> getAllCommunites() {
        List<Community> communityList = communityRepository.findAll();
        return communityList.stream()
                .map(communityMapper::toDTO) // Mapper 를 사용하여 변환
                .collect(Collectors.toList());
    }

    @Override
    public CommunityDTO getCommunityById(Long id) {
        Community community = communityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));
        return communityMapper.toDTO(community);
    }


}
