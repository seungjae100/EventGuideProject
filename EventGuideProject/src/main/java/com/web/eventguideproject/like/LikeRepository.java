package com.web.eventguideproject.like;

import com.web.eventguideproject.community.Community;
import com.web.eventguideproject.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    boolean existsByCommunityAndMember(Community community, Member member);
    Like findByCommunityAndMember(Community community, Member member);
    int countByCommunity(Community community);
}
