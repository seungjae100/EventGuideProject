import React from "react";

const CommentList = ({ comments, onEdit, onDelete }) => {
    return (
        <div>
            {comments.map(comment => (
                <div key={comment.id}>
                    <p>
                        <strong>{comment.nickName}</strong> {/* 작성자 닉네임 표시 */}
                        : {comment.content}
                    </p>
                    <button onClick={() => onEdit(comment)}>수정</button>
                    <button onClick={() => onDelete(comment.id)}>삭제</button>
                </div>
            ))}
        </div>
    );
};

export default CommentList;