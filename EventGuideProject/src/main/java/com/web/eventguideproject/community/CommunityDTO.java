package com.web.eventguideproject.community;

import com.web.eventguideproject.comments.CommentDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class CommunityDTO {

    private Long id;
    private String title;
    private String content;
    private int likes;
    private List<CommentDTO> comments;
    private String authorNickName; // 작성자의 닉네임, Member 엔티티에서 가져옵니다.
}
