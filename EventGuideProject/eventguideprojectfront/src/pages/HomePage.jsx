import React, {useEffect, useState} from "react";
import {useNavigation} from "../hooks/useNavigation";


function HomePage() {
    const { goToRegister, goToLogin, goToHomePage, goToMypage } = useNavigation();
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    useEffect(() => {
        const token = localStorage.getItem('token');
        if (token) {
            setIsLoggedIn(true);
        }
    }, []);

    const handleLogout = () => {
        localStorage.removeItem('token');

        setIsLoggedIn(false);
        goToHomePage();
    }
    return (
        <div>
            <h1>test</h1>
            <div>
                {isLoggedIn ? (
                <>
                    <button onClick={goToMypage}>마이페이지</button>
                    <button onClick={handleLogout}>로그아웃</button>
                </>
            ) : (
                <>
                    <button onClick={goToRegister}>회원가입</button>
                    <button onClick={goToLogin}>로그인</button>
                </>
                )}
            </div>
        </div>
    );
}

export default HomePage;