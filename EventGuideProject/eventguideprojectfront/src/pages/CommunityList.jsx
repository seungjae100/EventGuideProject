import React, {useEffect, useState} from "react";
import axiosInstance from "../utils/axiosInstance";
import {Link, useNavigate} from "react-router-dom";
import '../styles/Global.css';

function CommunityList() {
    const [communites, setCommunities] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchCommunites = async () => {
            try {
                const response = await axiosInstance.get('api/community/list');
                setCommunities(response.data);
            } catch (error) {
                console.error("커뮤니티 목록을 불러오지 못했습니다.", error);
            }
        };

        fetchCommunites();
    }, []);

    // 게시글 작성 버튼 클릭 시 게시글 작성 페이지로 이동하는 함수
    const handleWriteClick = () => {
        navigate('/community/write');  // 게시글 작성 페이지로 이동
    };

    return (
        <div className="container">
            <h2>커뮤니티 전체 목록</h2>
            <button onClick={handleWriteClick}>게시글 작성</button>
            <ul>
                {communites.map(community => (
                    <li key={community.id}>
                        <Link to={`/community/${community.id}`}>
                            {community.title}
                        </Link>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default CommunityList;

