package com.web.eventguideproject.community;

import com.web.eventguideproject.comments.CommentDTO;
import com.web.eventguideproject.uploadfile.UploadFile;

import com.web.eventguideproject.uploadfile.UploadFileDTO;
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
    private int likeCount;  // 좋아요 수
    private List<CommentDTO> comments;
    private String nickName; // 작성자의 닉네임, Member 엔티티에서 가져옵니다.
    private UploadFileDTO uploadFile; // 업로드된 파일 정보

}
