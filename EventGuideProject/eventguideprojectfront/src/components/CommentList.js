import React from "react";

const CommentList = ({ comments, onEdit, onDelete, editingCommentId, editedContent, onEditSubmit, onCancelEdit, setEditedContent }) => {
    return (
        <div>
            {comments.map(comment => (
                <div key={comment.id}>
                    {editingCommentId === comment.id ? (
                        <div>
                            <textarea
                                value={editedContent}
                                onChange={(e) => setEditedContent(e.target.value)}
                                placeholder="수정할 댓글 내용을 입력하세요."
                            />
                            <button onClick={() => onEditSubmit(comment.id)}>저장</button>
                            <button onClick={onCancelEdit}>취소</button>
                        </div>
                    ) : (
                        <div>
                        <p>{comment.content}</p>
                        <button onClick={() => onEdit(comment.id, comment.content)}>수정</button>
                        <button onClick={() => onDelete(comment.id)}>삭제</button>
                        </div>
                    )}
                </div>
            ))}
        </div>
    );
};

export default CommentList;