import React from "react";
import {useNavigation} from "../hooks/useNavigation";


function HomePage() {
    const { goToRegister } = useNavigation();

    return (
        <div>
            <h1>test</h1>
            <div>
                <button onClick={goToRegister}>회원가입</button>
            </div>
        </div>
    );
}

export default HomePage;