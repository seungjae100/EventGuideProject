package com.web.eventguideproject.comments;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 댓글 생성
    @PostMapping("/community/{communityId}/create")
    public ResponseEntity<CommentDTO> createComment(@PathVariable Long communityId,
                                                    @RequestBody CommentDTO commentDTO) {
        CommentDTO createComment = commentService.createComment(communityId, commentDTO.getMemberId(), commentDTO.getContent());
        return ResponseEntity.ok(createComment);
    }
    // 특정 게시글에 대한 댓글 목록 조회
    @GetMapping("/community/{communityId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByCommunityId(@PathVariable Long communityId) {
        List<CommentDTO> comments = commentService.getCommentsByCommunityId(communityId);
        return ResponseEntity.ok(comments);
    }

    // 댓글 수정
    @PutMapping("/{commentId}/update")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Long commentId,
                                                    @RequestBody CommentDTO commentDTO) {
        CommentDTO updatedComment = commentService.updateComment(commentId, commentDTO.getContent());
        return ResponseEntity.ok(updatedComment);

    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}/delete")
    public ResponseEntity<CommentDTO> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }
}