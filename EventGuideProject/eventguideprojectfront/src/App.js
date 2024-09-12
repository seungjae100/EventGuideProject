import React from "react";
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from "./pages/HomePage";
import Register from "./pages/Register";
import Login from "./pages/Login";
import CommunityWrite from "./components/CommunityWrite";
import CommunityList from "./pages/CommunityList";
import CommunityDetail from "./pages/CommunityDetail";
import CommunityUpdate from "./pages/CommunityUpdate";




function App() {
  return (
      <Router>
            <Routes>
                <Route path="/" element={<HomePage />} />
                <Route path="/register" element={<Register />} />
                <Route path="/login" element={<Login />} />
                <Route path="/community" element={<CommunityList />} />
                <Route path="/community/:id" element={<CommunityDetail />} />
                <Route path="/community/write" element={<CommunityWrite />} />
                <Route path="/community/update/:id" element={<CommunityUpdate />} />
        </Routes>
      </Router>
  );
}

export default App;
