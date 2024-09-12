import {useParams, useNavigate} from "react-router-dom";
import React, {useEffect, useState} from "react";
import axiosInstance from "../utils/axiosInstance";
import CommentForm from "../components/CommentForm";
import CommentList from "../components/CommentList";
import LikeButton from "../components/LikeButton";

function CommunityDetail() {
    const { id } = useParams();
    const [community, setCommunity] = useState(null); // 커뮤니티 글 데이터
    const [comments, setComments] = useState([]); // 댓글 데이터
    const [editingCommentId, setEditingCommentId] = useState(null); // 수정 중인 댓글의 ID 저장
    const [editedContent, setEditedContent] = useState(''); // 수정할 댓글 내용 저장
    const navigate = useNavigate();
    const memberId = localStorage.getItem('memberId'); // memberId를 숫자로 변환

    // 게시글 및 댓글 데이터를 불러오는 함수
    useEffect(() => {
        const fetchCommunity = async () => {
            try {
                const response = await axiosInstance.get(`/api/community/${id}`);
                setCommunity(response.data);
            } catch (error) {
                console.error("게시글을 불러오던 중 오류 발생: ", error );
            }
        };

        const fetchComments = async () => {
            try {
                const response = await axiosInstance.get(`/api/comments/community/${id}`);
                setComments(response.data);
            } catch (error) {
                console.error("댓글을 불러오던 중 오류 발생: ", error);
            }
        };

        fetchCommunity();
        fetchComments();
    }, [id]);

    // 댓글 작성 함수
    const handleCommentSubmit = async (newComment) => {
        try {
            // 댓글 작성 API 호출
            await axiosInstance.post(`/api/comments/community/${id}/create`, {
                memberId: memberId,     // 게시글 ID
                content: newComment, // 새 댓글 내용
            });

            // 새로 작성된 댓글 목록을 다시 불러옴
            const response = await  axiosInstance.get(`/api/comments/community/${id}`);
            setComments(response.data);
        } catch (error) {
            console.log("댓글 작성 중 오류 발생: ", error);
        }
    };

    // 댓글 수정 모드로 전환하는 함수
    const handleEditClick = (commentId, currentContent) => {
        setEditingCommentId(commentId); // 수정할 댓글의 ID 저장
        setEditedContent(currentContent); // 수정할 댓글의 기존 내용을 저장
    };

    // 댓글 수정 함수
    const handleEditSubmit = async (commentId) => {
        try {
            await axiosInstance.put(`/api/comments/${commentId}/update`, {
                content: editedContent, // 수정할 내용 전달
            });

            const response = await axiosInstance.get(`/api/comments/community/${id}`);
            setComments(response.data);

            // 수정 모드 종료
            setEditingCommentId(null);
            setEditedContent('');
        } catch (error) {
            console.error("댓글 수정 중 오류 발생: ", error);
        }
    };

    // 댓글 삭제 함수
    const handleCommentDelete = async (commentId) => {
        try {
            await axiosInstance.delete(`/api/comments/${commentId}/delete`);
            setComments(comments.filter(c => c.id !== commentId));  // 삭제된 댓글 목록에서 제거
        } catch (error) {
            console.error("댓글 삭제 중 오류 발생: ", error);
        }
    };

    if (!community) {
        return <div>로딩 중...</div>;
    }
    // 게시글 수정 페이지로 이동하는 함수
    const goToCommunityUpdate = () => {
        navigate(`/community/update/${id}`);
    };

    return (
        <div>
            <h2>{community.title}</h2>
            <p>{community.content}</p>
            <p>작성자: {community.nickName}</p>
            <LikeButton communityId={id}/>
            <button onClick={goToCommunityUpdate}>게시글 수정</button>

            <h3>댓글</h3>
            <CommentList
                comments={comments}
                onEdit={handleEditClick}
                onDelete={handleCommentDelete}
                editingCommentId={editingCommentId}
                editedContent={editedContent}
                onEditSubmit={handleEditSubmit}
                onCancelEdit={() => setEditingCommentId(null)}
                setDeitedContent={setEditedContent}
            />
            <CommentForm onSubmit={handleCommentSubmit} />
        </div>
    );
}

export default CommunityDetail;