import React from "react";
import {useNavigate} from "react-router-dom";
import '../styles/Header.css';


function Header() {
    const { goToHomePage, goToCommunityList, goToRegister, goToLogin } = useNavigate();
    const token = localStorage.getItem('token');

    const handleLogout = () => {
        localStorage.removeItem('token');
        goToHomePage();
    }

    return (
        <header className="header">
            <div className="logo" onClick={goToHomePage}>
                <img src="/images/logo.png" alt="로고" className="logo-image"/>
            </div>
            <nav className="nav">
                <button onClick={goToCommunityList}>커뮤니티</button>
                {token ? (
                    <>
                        <button onClick={handleLogout}>로그아웃</button>
                    </>
                ) : (
                    <>
                        <button onClick={goToRegister}>회원가입</button>
                        <button onClick={goToLogin}>로그인</button>
                    </>
                )}
            </nav>
        </header>
    );
}

export default Header;
