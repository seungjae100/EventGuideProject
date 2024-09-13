import React from "react";
import '../styles/Footer.css';

function Footer() {
    return (
    <footer className="footer">
        <div className="company-info">
            <p>회사 이름</p>
            <p>회사 전화번호</p>
            <p>회사 주소</p>
        </div>
        <div className="social-links">
            <a href="https://github.com/seungjae100/EventGuideProject" target="_blank" rel="noopener noreferrer">
                <img src="/images/githubcat.png" alt="Github Logo" className="social-logo"/>
            </a>
        </div>
    </footer>
    );
}

export default Footer;