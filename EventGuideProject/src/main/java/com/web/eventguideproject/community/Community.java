package com.web.eventguideproject.community;

import com.web.eventguideproject.comments.Comment;
import com.web.eventguideproject.member.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 커뮤니티 글의 아이디

    @NotBlank
    private String title; // 커뮤니티 글의 제목

    @NotBlank
    @Lob
    private String content; // 커뮤니티 글의 내용

    private String uploadFile; // 커뮤니티 글의 업로드 가능한 파일

    private int likes; // 커뮤니티 글의 좋아요

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>(); // 커뮤니티 글에 대한 댓글을 연동하기 위한 필드

    @ManyToOne(fetch = FetchType.LAZY) // Member 와 다대일 관계 설정
    @JoinColumn(name = "member_id")
    private Member member;

    @Temporal(TemporalType.TIMESTAMP)
    private Date CreatedAt = new Date(); // 커뮤니티 글의 작성시간

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date(); // 커뮤니티 글의 수정시간

    // 작성자의 닉네임을 반환하는 메서드
    public String getAuthorNickName() {
        return this.member != null ? this.member.getNickName() : null;
    }

}
