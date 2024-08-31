import { useNavigate } from "react-router-dom";

export const useNavigation = () => {
    const navigate = useNavigate();

    const goToRegister = () => navigate('/register');
    const goToHomePage = () => navigate('/');

    return { goToHomePage, goToRegister };
};