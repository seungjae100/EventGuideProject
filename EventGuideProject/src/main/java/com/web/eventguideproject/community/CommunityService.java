package com.web.eventguideproject.community;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CommunityService  {

    CommunityDTO write(CommunityDTO communityDTO, MultipartFile uploadFile) throws IOException; // 커뮤니티 게시글 작성로직을 위한 메서드

}
