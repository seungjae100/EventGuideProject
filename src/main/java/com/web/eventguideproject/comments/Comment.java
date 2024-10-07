package com.web.eventguideproject.comments;


import com.web.eventguideproject.community.Community;
import com.web.eventguideproject.member.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 댓글의 고유 아이디

    @Lob
    private String content; // 댓글의 내용

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date(); // 댓글 작성 시간

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date(); // 댓글 수정 시간

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;               // 댓글 작성자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id")
    private Community community;         // 댓글이 달린 게시글
}
