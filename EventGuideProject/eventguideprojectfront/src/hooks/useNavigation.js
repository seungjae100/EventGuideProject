import { useNavigate } from "react-router-dom";

export const useNavigation = () => {
    const navigate = useNavigate();

    const goToRegister = () => navigate('/register')
    const goToHomePage = () => navigate('/')
    const goToLogin = () => navigate('/login')
    const goToMypage = () => navigate('/mypage')
    const goToCommunityWrite = () => navigate('/community/write')
    const goToCommunityList = () => navigate('/community')


    const goToCommunityUpdate = (id) => navigate(`/community/update/${id}`)


    return { goToHomePage, goToRegister, goToLogin, goToMypage, goToCommunityWrite, goToCommunityList, goToCommunityUpdate };
};