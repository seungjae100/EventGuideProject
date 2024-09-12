package com.web.eventguideproject.like;

import com.web.eventguideproject.community.Community;
import com.web.eventguideproject.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "likes") // like 는 예약어이기에 사용하기 힘들다
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member; // 좋아요를 누른 사용자

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community; // 좋아요한 게시글
}
