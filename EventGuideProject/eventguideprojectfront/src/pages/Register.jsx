import React, { useState } from "react";
import axiosInstance from "../utils/axiosInstance";
import useValidation from "../hooks/useValidation";

function Register() {
    const [formData, setFormData] = useState({
        memberId: "",
        password: "",
        passwordok: "",
        email: "",
        memberName: "",
        nickName: "",
        birth: "",
        sex: "",
        phone: "",
        address: "",
    });

    const { validateForm } = useValidation(formData);

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value,
        });
    }

    const handleSubmit = async (e) => {
        e.preventDefault();

        const validationError = validateForm();
        if (validationError) {
            alert(validationError);
            return;
        }

        const formattedBirth = new Date(
            `${formData.birth.slice(0,4)}-${formData.birth.slice(4,6)}-${formData.birth.slice(6,8)}`
        );

        try {
            const response = await axiosInstance.post("/api/members/register", {
                ...formData,
                birth: formattedBirth,
            });
            console.log(response);  // 응답 전체를 확인
            alert("회원가입이 성공적으로 완료되었습니다.");
        } catch (error) {
            console.error("회원가입 중 오류가 발생했습니다.", error); // 에러 전체를 확인
            alert("회원가입에 실패했습니다.");
        }
    };

    return (
        <div className="register-container">
            <h2>회원가입</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="memberId">아이디</label>
                    <input
                        type="text"
                        id="memberId"
                        name="memberId"
                        value={formData.memberId}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="password">비밀번호</label>
                    <input
                        type="password"
                        id="password"
                        name="password"
                        value={formData.password}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="passwordok">비밀번호 확인</label>
                    <input
                        type="password"
                        id="passwordok"
                        name="passwordok"
                        value={formData.passwordok}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="email">이메일</label>
                    <input
                        type="email"
                        id="email"
                        name="email"
                        value={formData.email}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="memberName">이름</label>
                    <input
                        type="text"
                        id="memberName"
                        name="memberName"
                        value={formData.memberName}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="nickName">닉네임</label>
                    <input
                        type="text"
                        id="nickName"
                        name="nickName"
                        value={formData.nickName}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="birth">생년월일</label>
                    <input
                        type="text"
                        id="birth"
                        name="birth"
                        value={formData.birth}
                        onChange={handleChange}
                        placeholder="YYYYMMDD"
                        required
                    />
                </div>
                <div>
                    <label>성별</label>
                    <div>
                        <input
                            type="radio"
                            id="male"
                            name="sex"
                            value="남"
                            checked={formData.sex === "남"}
                            onChange={handleChange}
                        />
                        <label htmlFor="male">남자</label>
                        <input
                            type="radio"
                            id="female"
                            name="sex"
                            value="여"
                            checked={formData.sex === "여"}
                            onChange={handleChange}
                        />
                        <label htmlFor="female">여자</label>
                    </div>
                </div>
                <div>
                    <label htmlFor="phone">핸드폰 번호</label>
                    <input
                        type="text"
                        id="phone"
                        name="phone"
                        value={formData.phone}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="address">주소</label>
                    <input
                        type="text"
                        id="address"
                        name="address"
                        value={formData.address}
                        onChange={handleChange}
                        required
                    />
                </div>
                <button type="submit">회원가입</button>
            </form>
        </div>
    );
}

export default Register;