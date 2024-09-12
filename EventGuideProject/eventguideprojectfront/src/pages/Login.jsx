import React, {useState} from "react";
import InputField from "../components/InputField";
import axiosInstance from "../utils/axiosInstance";
import { useNavigate } from "react-router-dom";

function Login() {
    const [ formData, setFormData ] = useState({ memberId: "", password: ""});
    const navigate = useNavigate()

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axiosInstance.post("/api/auth/login", formData);
            if (response.status === 200) {
                const token = response.data.token; // 서버에서 토큰을 가져옵니다.
                const memberId = formData.memberId; // formData 에서 memberId를 가져옵니다.
                console.log(token);
                localStorage.setItem('token', token); // 토큰을 로컬스토리지에 저장합니다.
                localStorage.setItem('memberId', memberId);
                alert("로그인 성공");
                navigate('/');
            }
        } catch (error) {
            alert("로그인 실패");
        }
    };

    return (
        <div>
            <h2>로그인</h2>
            <form onSubmit={handleSubmit}>
                <InputField
                    id="memberId"
                    label="아이디"
                    value={formData.memberId}
                    onChange={handleChange}
                    required
                />
                <InputField
                    id="password"
                    label="비밀번호"
                    type="password"
                    value={formData.password}
                    onChange={handleChange}
                    required
                />
                <button type="submit">로그인</button>
            </form>
        </div>
    );
}

export default Login;