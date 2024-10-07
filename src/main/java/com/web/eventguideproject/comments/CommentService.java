package com.web.eventguideproject.comments;

import java.util.List;

public interface CommentService {

    // 댓글 작성
    CommentDTO createComment(Long communityId, String memberId, String content);

    // 댓글 조회
    List<CommentDTO> getCommentsByCommunityId(Long communityId);

    // 댓글 수정
    CommentDTO updateComment(Long commentId, String content);

    // 댓글 삭제
    void deleteComment(Long commentId);
}
