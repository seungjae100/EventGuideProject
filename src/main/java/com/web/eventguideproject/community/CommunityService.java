package com.web.eventguideproject.community;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CommunityService  {
    // 커뮤니티 게시글 작성로직을 위한 메서드
    CommunityDTO write(CommunityDTO communityDTO, MultipartFile uploadFile) throws IOException;

    // 커뮤니티 게시글 목록 조회 메서드
    List<CommunityDTO> getAllCommunites();

    // 특정 커뮤니티 게시글 조회 메서드
    CommunityDTO getCommunityById(Long id);

    // 게시글 수정 메서드
    CommunityDTO update(Long id, CommunityDTO communityDTO, MultipartFile uploadFile ) throws IOException;

    // 게시글 삭제 메서드
    void delete(Long id);
}
