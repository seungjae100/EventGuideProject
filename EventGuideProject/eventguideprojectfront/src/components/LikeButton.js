import {useEffect, useState} from "react";
import axiosInstance from "../utils/axiosInstance";

function LikeButton({ communityId }) {
    const [likeCount, setLikeCount] = useState(0);
    const [liked, setLiked] = useState(false);
    const memberId = localStorage.getItem('memberId'); // 현재 로그인한 사용자

    useEffect(() => {
        // 좋아요 수 조회
        const fetchLikeCount = async () => {
            try {
                const response = await  axiosInstance.get(`api/likes/community/${communityId}/count`);
                setLikeCount(response.data);
            } catch (error) {
                console.error("좋아요 수를 불러오던 중 오류가 발생했습니다.",  error);
            }
        };

        // 좋아요 상태 조회
        const fetchLikedStatus = async () => {
            try {
                const response = await axiosInstance.get(`api/likes/community/${communityId}/status`, {
                  params: {memberId}
                });
                setLiked(response.data); // 서버에서 받은 좋아요 상태 설정
            } catch (error) {
                console.error("좋아요 상태를 불러오던 중 오류 발생: ", error);
            }
        }

        fetchLikeCount();
        fetchLikedStatus()
    }, [communityId, memberId]);

    const handleLikeClick = async () => {
        try {
            if (liked) {
                // 좋아요 취소
                await axiosInstance.delete(`api/likes/community/${communityId}/remove`, {
                    params: {memberId}
                });
                setLikeCount(likeCount -1 );
            } else {
                // 좋아요 추가
                await axiosInstance.post(`api/likes/community/${communityId}/add`, null, {
                    params: {memberId}
                });
                setLikeCount(likeCount + 1 );
            }
            setLiked(!liked);
        } catch (error) {
            console.error("좋아요 처리 중 오류 발생: ", error);
        }
    };

    return (
        <div>
            <button onClick={handleLikeClick}>
                {liked ? "❤️" : "🤍"} {likeCount}
            </button>
        </div>
    );
}

export default LikeButton;