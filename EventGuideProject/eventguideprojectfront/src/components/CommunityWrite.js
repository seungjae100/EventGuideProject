import React, { useState } from "react";
import axiosInstance from "../utils/axiosInstance";
import InputField from "./InputField";

const CommunityWrite = () => {
    const [formData, setFormData] = useState({
        title:'',
        content:'',
        uploadFile: null,
    });

    const handleChange = (e) => {
        const {id, value} = e.target;
        setFormData({
            ...formData,
            [id]: value,
        });
    };

    const handleFileChange = (e) => {
        const file = e.target.files[0] || null; // 파일이 선택되지 않으면 null
        console.log(file); // 파일 정보 출력
        setFormData({
            ...formData,
            uploadFile: file,
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const postData = new FormData();
        postData.append('title', formData.title); // 제목
        postData.append('content', formData.content); // 내용
        postData.append('MultipartFile', formData.uploadFile); // 파일


        // FormData의 내용을 콘솔로 출력해서 확인
        for (let pair of postData.entries()) {
            console.log(`${pair[0]}, ${pair[1]}`);
        }

        try {
            const response = await axiosInstance.post('/api/community/write', postData);
            console.log('글 작성: ', response.data);
        } catch (error) {
            console.error('글 작성 오류:', error);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <InputField
                id="title"
                label="Title"
                value={formData.title}
                onChange={handleChange}
                required
            />
            <InputField
                id="content"
                label="Content"
                value={formData.content}
                onChange={handleChange}
                required
                type="textarea"
            />
            <div>
                <label htmlFor="uploadFile">File:</label>
                <input type="file" id="uploadFile" name="uploadFile" onChange={handleFileChange} />
            </div>
            <button type="submit">Submit</button>
        </form>
    );
};

export default CommunityWrite;