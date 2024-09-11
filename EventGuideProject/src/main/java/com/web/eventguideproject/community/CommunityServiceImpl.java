package com.web.eventguideproject.community;

import com.web.eventguideproject.auth.UserPrincipal;
import com.web.eventguideproject.member.Member;
import com.web.eventguideproject.member.MemberRepository;
import com.web.eventguideproject.uploadfile.UploadFile;
import com.web.eventguideproject.uploadfile.UploadFileDTO;
import com.web.eventguideproject.uploadfile.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private CommunityRepository communityRepository; // 커뮤니티 정보 접근

    @Autowired
    private MemberRepository memberRepository; // 로그인한 회원 정보 접근

    @Autowired
    private UploadFileService uploadFileService; // 업로드한 파일 정보 접근

    // 게시글을 작성하는 메서드 추가

    @Override
    public CommunityDTO write(CommunityDTO communityDTO, MultipartFile uploadFile) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Long memberSeq = userPrincipal.getSeq();

        Member member = memberRepository.findById(memberSeq)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자입니다."));

        Community community = new Community();
        community.setTitle(communityDTO.getTitle());
        community.setContent(communityDTO.getContent());
        community.setMember(member);

        // 파일 저장
        if (uploadFile != null && !uploadFile.isEmpty()) {
            UploadFile savedFile = uploadFileService.saveFile(uploadFile);
            community.setUploadFile(savedFile);
            System.out.println("UploadFile ID: " + savedFile.getId());
        }
        communityRepository.save(community);
        // Community 엔티티 -> CommunityDTO 변환
        return convertToDTO(community);
    }

    // 엔티티 -> DTO 변환 메서드 추가
    private CommunityDTO convertToDTO(Community community) {
        CommunityDTO communityDTO = new CommunityDTO();
        communityDTO.setId(community.getId());
        communityDTO.setTitle(community.getTitle());
        communityDTO.setContent(community.getContent());
        communityDTO.setLikes(community.getLikes());
        communityDTO.setNickName(community.getMember().getNickName());

        // UploadFile -> UploadFileDTO 변환
        if (community.getUploadFile() != null) {
            UploadFileDTO uploadFileDTO = new UploadFileDTO();
            uploadFileDTO.setFileName(community.getUploadFile().getFileName());
            uploadFileDTO.setFilePath(community.getUploadFile().getFilePath());
            uploadFileDTO.setFileSize(community.getUploadFile().getFileSize());
            uploadFileDTO.setFileType(community.getUploadFile().getFileType());
            communityDTO.setUploadFile(uploadFileDTO);
        }

        return communityDTO;
    }

    // 게시글을 수정하는 메서드
    @Override
    public CommunityDTO update(Long id, CommunityDTO communityDTO, MultipartFile uploadFile) throws IOException {
        // 수정할 게시글 ID로 커뮤니티 조회
        Community community = communityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));

        // 게시글 제목과 내용 수정
        community.setTitle(communityDTO.getTitle());
        community.setContent(communityDTO.getContent());

        // 파일 수정
        if (uploadFile != null && !uploadFile.isEmpty()) {
            UploadFile savedFile = uploadFileService.saveFile(uploadFile);
            community.setUploadFile(savedFile);
        }

        // 수정된 게시글 저장
        communityRepository.save(community);

        // DTO로 변환하여 반환
        return convertToDTO(community);
    }


    // 게시글을 삭제하는 메서드
    @Override
    public void delete(Long id) {
        Community community = communityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));
        communityRepository.delete(community);
    }

    @Override
    public List<CommunityDTO> getAllCommunites() {
        List<Community> communityList = communityRepository.findAll();
        return communityList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CommunityDTO getCommunityById(Long id) {
        Community community = communityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));
        return convertToDTO(community);
    }


}