import React, { useState } from "react";
import axiosInstance from "../utils/axiosInstance";
import InputField from "../components/InputField";

const Community = () => {
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
        setFormData({
            ...formData,
            uploadFile: e.target.files[0],
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const postData = new FormData();
        postData.append('title', formData.title);
        postData.append('content', formData.content);
        postData.append('uploadFile', formData.uploadFile); // 파일 필드 추가

        try {
            const response = await axiosInstance.post('/api/community/write', postData);
            console.log('글 작성: ', response.data);
            // 성공적으로 게시글이 작성된 후 처리할 로직 추가 가능
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

export default Community;