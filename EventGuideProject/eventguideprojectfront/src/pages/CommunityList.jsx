import React, {useEffect, useState} from "react";
import axiosInstance from "../utils/axiosInstance";
import {Link} from "react-router-dom";

function CommunityList() {
    const [communites, setCommunities] = useState([]);

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

    return (
        <div>
            <h2>커뮤니티 전체 목록</h2>
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

