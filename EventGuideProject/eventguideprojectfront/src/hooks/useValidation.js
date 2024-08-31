import {validateEmail, validateMemberId, validatePassword, validatePhone} from "../utils/validation";


const useValidation = (formData) => {
    const validateForm = () => {
        if (!validateMemberId(formData.memberId)) {
            return "아이디는 8자 이상 16자 이하의 영문과 숫자만 포함해야합니다.";
        }

        if (!validatePassword(formData.password)) {
            return "비밀번호는 8자 이상 16자 이하의 소문자와 숫자만 포함해야합니다.";
        }

        if (formData.password !== formData.passwordok) {
            return "비밀번호가 일치하지 않습니다.";
        }

        if (!validateEmail(formData.email)) {
            return "올바른 이메일 형식이 아닙니다.";
        }

        if (!validatePhone(formData.phone)) {
            return "핸드폰 번호는 11자리 숫자여야합니다.";
        }

        return null;
    };

    return {
        validateForm,
    };
};

export default useValidation;