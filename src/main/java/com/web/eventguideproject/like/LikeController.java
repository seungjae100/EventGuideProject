package com.web.eventguideproject.like;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;

    // 좋아요 추가
    @PostMapping("/community/{communityId}/add")
    public ResponseEntity<String> addLike(@PathVariable Long communityId,
                                          @RequestParam String memberId) {
        boolean success = likeService.addLike(communityId, memberId);
        if (success) {
            return ResponseEntity.ok("좋아요가 추가되었습니다.");
        } else {
            return ResponseEntity.badRequest().body("이미 좋아요를 눌렀습니다.");
        }
    }

    // 좋아요 취소
    @DeleteMapping("/community/{communityId}/remove")
    public ResponseEntity<String> removeLike(@PathVariable Long communityId,
                                             @RequestParam String memberId) {
        boolean success = likeService.removeLike(communityId, memberId);
        if (success) {
            return ResponseEntity.ok("좋아요가 취소되었습니다.");
        } else {
            return ResponseEntity.badRequest().body("좋아요를 누르지 않았습니다.");
        }
    }

    // 좋아요 수 조회
    @GetMapping("/community/{communityId}/count")
    public ResponseEntity<Integer> getLikeCount(@PathVariable Long communityId) {
        int likeCount = likeService.getLikeCount(communityId);
        return ResponseEntity.ok(likeCount);
    }

    // 좋아요 상태 확인
    @GetMapping("/community/{communityId}/status")
    public ResponseEntity<Boolean> getLikeStatus(@PathVariable Long communityId,
                                                 @RequestParam String memberId) {
        boolean liked = likeService.isLikedByMember(communityId, memberId);
        return ResponseEntity.ok(liked);
    }
}
