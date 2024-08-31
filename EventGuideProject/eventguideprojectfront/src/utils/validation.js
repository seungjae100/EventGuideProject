// 유효성 검사를 위한 함수들을 정의

// 회원가입 유저의 아이디 유효성 검사
export const validateMemberId = (memberId) => {
    const regex = /^[a-zA-Z0-9]{8,16}$/;
    return regex.test(memberId);
};
// 회원가입 유저의 비밀번호 유효성 검사
export const validatePassword = (password) => {
    const regex = /^(?=.*[a-z])(?=.*\d)[a-z\d]{8,16}$/;
    return regex.test(password);
};
// 회원가입 유저의 이메일 유효성 검사
export const validateEmail = (email) => {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
};
// 회원가입 유저의 핸드폰번호 유효성 검사
export const validatePhone = (phone) => {
    const regex = /^\d{11}$/;
    return regex.test(phone);
};