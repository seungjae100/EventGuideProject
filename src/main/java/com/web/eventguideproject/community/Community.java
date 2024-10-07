package com.web.eventguideproject.community;

import com.web.eventguideproject.comments.Comment;
import com.web.eventguideproject.member.Member;
import com.web.eventguideproject.uploadfile.UploadFile;
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // 파일을 관리하는 관계 설정
    @JoinColumn(name = "upload_file_id")
    private UploadFile uploadFile; // 커뮤니티 글에 업로드된 파일

    @Column(nullable = false)
    private int likeCount = 0;  // 좋아요 수를 관리할 필드

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>(); // 커뮤니티 글에 대한 댓글을 연동하기 위한 필드

    @ManyToOne(fetch = FetchType.LAZY) // Member 와 다대일 관계 설정
    @JoinColumn(name = "member_id")
    private Member member;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date(); // 커뮤니티 글의 작성시간

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date(); // 커뮤니티 글의 수정시간

}
