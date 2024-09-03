import { useNavigate } from "react-router-dom";

export const useNavigation = () => {
    const navigate = useNavigate();

    const goToRegister = () => navigate('/register');
    const goToHomePage = () => navigate('/');
    const goToLogin = () => navigate('/login')
    const goToMypage = () => navigate('/Mypage')

    return { goToHomePage, goToRegister, goToLogin, goToMypage };
};