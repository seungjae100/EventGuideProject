import React from "react";
import {BrowserRouter, BrowserRouter as Router, Route, Routes, useLocation} from 'react-router-dom';
import HomePage from "./pages/HomePage";
import Register from "./pages/Register";
import Login from "./pages/Login";
import CommunityWrite from "./components/CommunityWrite";
import CommunityList from "./pages/CommunityList";
import CommunityDetail from "./pages/CommunityDetail";
import CommunityUpdate from "./pages/CommunityUpdate";
import Header from "./components/Header";
import Footer from "./components/Footer";

function App() {
    const location = useLocation(); // 현재 위치 정보 가져오기

    // 로그인 및 회원가입 페이지에서는 헤더와 푸터를 숨김
    const noHeaderFooterRoutes = ['/login', '/register'];
    const showHeaderFooter = !noHeaderFooterRoutes.includes(location.pathname); // 현재 페이지가 숨겨야 할 페이지인지 체크

    return (
        <>

            {showHeaderFooter && <Header />} {/* 조건부로 헤더 표시 */}
            <Routes>
                <Route path="/" element={<HomePage />} />
                <Route path="/register" element={<Register />} />
                <Route path="/login" element={<Login />} />
                <Route path="/community" element={<CommunityList />} />
                <Route path="/community/:id" element={<CommunityDetail />} />
                <Route path="/community/write" element={<CommunityWrite />} />
                <Route path="/community/update/:id" element={<CommunityUpdate />} />
            </Routes>
            {showHeaderFooter && <Footer />} {/* 조건부로 푸터 표시 */}

        </>
    );
}

export default App;