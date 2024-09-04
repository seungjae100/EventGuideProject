package com.web.eventguideproject.comments;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {

    private Long id;
    private String content;
    private String author;
    private Long communityId; // 어떤 커뮤니티 게시글에 속한 댓글인지 나타내기 위한 필드

}
