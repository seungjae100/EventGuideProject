package com.web.eventguideproject.comments;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
    private Long id;
    private Long communityId; // 댓글이 속한 게시글 ID
    private String content;
    private String memberId;  // 작성자의 memberId
    private String nickName;  // 작성자의 닉네임
    private Long createdAt;   // 작성 시간

    // 기본 생성자
    public CommentDTO() {
    }

    // Comment 엔티티를 기반으로 CommentDTO를 생성하는 생성자
    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.memberId = comment.getMember().getMemberId();  // Member 엔티티에 memberId 필드 사용
        this.nickName = comment.getMember().getNickName();  // Member 엔티티에 nickName 필드 사용
        this.communityId = comment.getCommunity().getId();
        this.createdAt = comment.getCreatedAt().getTime();
    }
}