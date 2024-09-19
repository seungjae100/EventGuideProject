import {useEffect, useState} from "react";
import axios from "axios";

const EventPage = ({ category }) => {
    const [events, setEvents] = useState([]);

    useEffect(() => {
        // 카테고리별로 API 호출
        const fetchEvents = () => {
            let endpoint = '';

            if (category === '전시회') {
                endpoint = '/api/exhibitions';
            } else if (category === '박람회') {
                endpoint = '/api/expos';
            } else if (category === '미술관') {
                endpoint = '/api/museums';
            }

            axios
                .get(endpoint)
                .then((response) =>  {
                    setEvents(response.data); // api 응답 데이터 상태에 저장
                })
                .catch((error) =>  {
                    console.error('행사정보를 불러오던 중 오류가 발생했습니다. :', error);
                });
        };

        fetchEvents();
    }, [category]); // 카테고리 값이 변경될 때마다 API 호출

    return (
        <div className="container">
            <h2>{category} 정보</h2>
            <ul>
                {events.map((event, index) => (
                    <li key={index}>
                        {event.museumNm} - {event.evntNm}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default EventPage;