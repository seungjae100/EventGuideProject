package com.web.eventguideproject.like;

public interface LikeService {

    boolean addLike(Long communityId, String memberId); // 좋아요 추가
    boolean removeLike(Long communityId, String memberId); // 좋아요 취소
    int getLikeCount(Long communityId); // 좋아요 수 조회
    boolean isLikedByMember(Long communityId,String memberId); // 특정 사용자가 해당 커뮤니티 글을 좋아요 눌렀는지 확인하는 메서드
}
