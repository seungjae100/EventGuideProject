import React, { useState } from "react";

const CommentForm = ({ onSubmit, initialContent = '', buttonText = '작성'}) => {
    const [ content, setContent ] = useState(initialContent);

    const handleSubmit = async () => {
        if (content.trim() !== '') {
            await onSubmit(content); // onSubmit 함수가 비동기일 때 await 사용
            setContent(''); // 폼 초기화
        }
    };

    return (
        <div>
            <textarea
                value={content}
                onChange={(e) => setContent(e.target.value)}
                placeholder="댓글을 입력하세요"
            />
            <button onClick={handleSubmit}>{buttonText}</button>
        </div>
    );
};

export default CommentForm;