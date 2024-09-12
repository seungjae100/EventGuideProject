import React, {useEffect, useState} from "react"; // 만약 이 부분을 빠트렸다면 추가해 주세요.
import InputField from "../components/InputField";
import {useNavigate, useParams} from "react-router-dom";
import axiosInstance from "../utils/axiosInstance"; // InputField 컴포넌트를 사용하는 경우 올바르게 임포트

function CommunityUpdate() {
    const { id } = useParams();
    const navigate = useNavigate();
    const [community, setCommunity] = useState({
        title: '',
        content: '',
        uploadFile: null
    });

    useEffect(() => {
        const fetchCommunity = async () => {
            try {
                const response = await axiosInstance.get(`/api/community/${id}`);
                console.log(response.data);  // 데이터 확인
                setCommunity(response.data); // 상태 업데이트
            } catch (error) {
                console.error("게시글을 불러오던 중 오류 발생: ", error);
            }
        };
        fetchCommunity();
    }, [id]);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        console.log(name, value);
        setCommunity({
            ...community,
            [name]: value
        });
    };

    const handleFileChange = (e) => {
        setCommunity({
            ...community,
            uploadFile: e.target.files[0]
        });
    };


    const handleSubmit = async (e) => {
        e.preventDefault();
        const formData = new FormData();
        formData.append('title', community.title);
        formData.append('content', community.content);
        if (community.uploadFile) {
            formData.append('uploadFile', community.uploadFile);
        }

        try {
            await axiosInstance.put(`/api/community/update/${id}`, formData);
            alert("게시글이 성공적으로 수정되었습니다.");
            navigate(`/api/community/${id}`); // 수정 후 상세 페이지로 이동
        } catch (error) {
            console.error("게시글 수정 중 오류 발생: ", error);
        }
    };

    return (
        <div>
            <h2>게시글 수정</h2>
            <form onSubmit={handleSubmit}>
                <InputField
                    id="title"
                    label="제목"
                    value={community.title}
                    onChange={handleInputChange}
                    required={true}
                />
                <InputField
                    id="content"
                    label="내용"
                    value={community.content}
                    onChange={handleInputChange}
                    required={true}
                    type="textarea"
                />
                <InputField
                    id="uploadFile"
                    label="파일 업로드"
                    type="file"
                    onChange={handleFileChange}
                    required={false}
                />
                <button type="submit">수정 완료</button>
            </form>
        </div>
    );
}

export default CommunityUpdate;