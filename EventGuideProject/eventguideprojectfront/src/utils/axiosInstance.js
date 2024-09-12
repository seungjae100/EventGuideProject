import axios from 'axios';

const axiosInstance = axios.create({
    baseURL: process.env.REACT_APP_API_BASE_URL,
});

// 요청 전에 실행되는 인터셉터 설정
axiosInstance.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem('token'); // 로컬 스토리지에서 토큰을 가져옴
        if (token) {
            config.headers.Authorization = `Bearer ${token}`; // 요청 헤더에 토큰 추가
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);


export default axiosInstance;