package com.web.eventguideproject.comments;

import com.web.eventguideproject.community.Community;
import com.web.eventguideproject.community.CommunityRepository;
import com.web.eventguideproject.member.Member;
import com.web.eventguideproject.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CommunityRepository communityRepository;

    // 댓글 작성
    @Override
    @Transactional
    public CommentDTO createComment(Long communityId, String memberId, String content) {
        Optional<Community> communityOptional = communityRepository.findById(communityId);
        if (!communityOptional.isPresent()) {
            throw new IllegalArgumentException("해당 커뮤니티 게시글이 존재하지 않습니다.");
        }

        Optional<Member> memberOptional = memberRepository.findByMemberId(memberId);
        if (!memberOptional.isPresent()) {
            throw new IllegalArgumentException("해당 멤버가 존재하지 않습니다.");
        }

        Community community = communityOptional.get();
        Member member = memberOptional.get();

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setMember(member); // 작성자 설정
        comment.setCommunity(community); // 댓글이 속한 게시글 설정
        commentRepository.save(comment);

        return new CommentDTO(comment);
    }

    // 특정 게시글에 대한 모든 댓글 조회
    @Override
    @Transactional
    public List<CommentDTO> getCommentsByCommunityId(Long communityId) {
        List<Comment> comments = commentRepository.findByCommunityId(communityId);
        return comments.stream().map(CommentDTO::new).toList(); // Comment 를 DTO 로 변환
    }

    // 댓글 수정
    @Override
    @Transactional
    public CommentDTO updateComment(Long commentId, String content) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (!commentOptional.isPresent()) {
            throw new IllegalArgumentException("해당 댓글이 존재하지 않습니다.");
        }

        Comment comment = commentOptional.get();
        comment.setContent(content); // 댓글 내용 수정
        comment.setUpdatedAt(new java.util.Date()); // 수정 시간 갱신
        commentRepository.save(comment);

        return new CommentDTO(comment);
    }

    // 댓글 삭제

    @Override
    @Transactional
    public void deleteComment(Long commentId) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (!commentOptional.isPresent()) {
            throw new IllegalArgumentException("해당 댓글이 존재하지 않습니다.");
        }
        commentRepository.deleteById(commentId);
    }
}