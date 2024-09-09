import {useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import axiosInstance from "../utils/axiosInstance";

function CommunityDetail() {
    const { id } = useParams();
    const [community, setCommnuity] = useState(null);

    useEffect(() => {
        const fetchCommunity = async () => {
            try {
                const response = await axiosInstance.get(`/api/community/${id}`);
                setCommnuity(response.data);
            } catch (error) {
                console.error("게시글을 불러오던 중 오류 발생: ", error );
            }
        };

        fetchCommunity();
    }, [id]);

    if (!community) {
        return <div>로딩 중...</div>;
    }

    return (
        <div>
            <h2>{community.title}</h2>
            <p>{community.content}</p>
            <p>작성자 : {community.nickName}</p>
            <p>좋아요 : {community.likes}</p>
        </div>
    );
}

export default CommunityDetail;