package com.web.eventguideproject.like;

import com.web.eventguideproject.comments.CommentRepository;
import com.web.eventguideproject.community.Community;
import com.web.eventguideproject.community.CommunityRepository;
import com.web.eventguideproject.member.Member;
import com.web.eventguideproject.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CommunityRepository communityRepository;

    @Override
    @Transactional
    public boolean addLike(Long communityId, String memberId) {
        // 게시글 찾기
        Optional<Community> communityOptional = communityRepository.findById(communityId);
        if (!communityOptional.isPresent()) {
            throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
        }

        // 사용자 찾기
        Optional<Member> memberOptional = memberRepository.findByMemberId(memberId);
        if (!memberOptional.isPresent()) {
            throw new IllegalArgumentException("해당 멤버가 존재하지 않습니다.");
        }

        Community community = communityOptional.get();
        Member member = memberOptional.get();

        // 좋아요 상태 확인
        if (likeRepository.existsByCommunityAndMember(community, member)) {
            return false;
        }

        // 좋아요 추가
        Like like = new Like();
        like.setCommunity(community);
        like.setMember(member);
        likeRepository.save(like);

        // 게시글의 좋아요 수 증가
        community.setLikeCount(community.getLikeCount() + 1);
        communityRepository.save(community);

        return true;
    }

    @Override
    @Transactional
    public boolean removeLike(Long communityId, String memberId) {
        // 게시글 찾기
        Optional<Community> communityOptional = communityRepository.findById(communityId);
        if (!communityOptional.isPresent()) {
            throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
        }

        // 사용자 찾기
        Optional<Member> memberOptional = memberRepository.findByMemberId(memberId);
        if (!memberOptional.isPresent()) {
            throw new IllegalArgumentException("해당 멤버가 존재하지 않습니다.");
        }

        Community community = communityOptional.get();
        Member member = memberOptional.get();

        // 좋아요 취소
        Like like = likeRepository.findByCommunityAndMember(community, member);
        if (like != null) {
            likeRepository.delete(like);

            // 게시글의 좋아요 수 감소
            community.setLikeCount(community.getLikeCount() -1);
            communityRepository.save(community);

            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public int getLikeCount(Long communityId) {
        Optional<Community> communityOptional = communityRepository.findById(communityId);
        if (!communityOptional.isPresent()) {
            throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
        }

        Community community = communityOptional.get();
        return likeRepository.countByCommunity(community);
    }

    @Override
    @Transactional
    public boolean isLikedByMember(Long communityId, String memberId) {
        Optional<Community> communityOptional = communityRepository.findById(communityId);
        if (!communityOptional.isPresent()) {
            throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
        }

        Optional<Member> memberOptional = memberRepository.findByMemberId(memberId);
        if (!memberOptional.isPresent()) {
            throw new IllegalArgumentException("해당 멤버가 존재하지 않습니다.");
        }
        Community community = communityOptional.get();
        Member member = memberOptional.get();

        return likeRepository.existsByCommunityAndMember(community, member);
    }
}
