import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import '../styles/Header.css';


function Header() {
    const navigate = useNavigate();
    const [isMenuOpen, setIsMenuOpen] = useState(false); // 메뉴의 열림/닫힘 상태를 관리
    const token = localStorage.getItem('token');

    const handleLogout = () => {
        localStorage.removeItem('token');
        navigate('/'); // 로그아웃 후 홈으로
    };

    const toggleMenu = () => {
        setIsMenuOpen(!isMenuOpen); // 햄버거 버튼 클릭 시 메뉴 열림/닫힘 상태 변경
    };

    return (
        <header className="header">
            <div className="logo" onClick={() => navigate('/')}>
                <img src="/images/eventlogo.png" alt="로고" className="logo-image"/>
            </div>

            {/* 햄버거 버튼 */}
            <div className="hamburger" onClick={toggleMenu}>
                {/*☰*/}
            </div>

            {/* 중앙 카테고리 메뉴 배치 */}
            <nav className={`nav category-nav ${isMenuOpen ? "open" : ""}`}>
                <ul>
                    <li>
                        <button onClick={() => navigate('/')}>Home</button>
                    </li>
                    <li className="dropdown">
                        <button>이벤트</button>
                        {/* 하위 메뉴 */}
                        <ul className="dropdown-menu">
                            <li>
                                <button onClick={() => navigate('/api/expos')}>박람회</button>
                            </li>
                            <li>
                                <button onClick={() => navigate('/api/exhibitions')}>전시회</button>
                            </li>
                            <li>
                                <button onClick={() => navigate('/api/museums')}>미술관</button>
                            </li>
                        </ul>
                    </li>
                    <li>
                    <button onClick={() => navigate('/map')}>지도</button>
                    </li>
                    <li>
                        <button onClick={() => navigate('/community')}>커뮤니티</button>
                    </li>
                </ul>
            </nav>

            {/* 우측 로그인/ 로그아웃 */}
            <nav className="user-nav">
                {token ? (
                    <>
                        {/*<button onClick={goToMyPage}>마이페이지</button>*/}
                        <button onClick={handleLogout}>로그아웃</button>
                    </>
                ) : (
                    <>
                        <button onClick={() => navigate('/register')}>회원가입</button>
                        <button onClick={() => navigate('/login')}>로그인</button>
                    </>
                )}
            </nav>
        </header>
    );
}

export default Header;
