import React, {ChangeEvent, useState} from 'react';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import MainPage from "./pages/MainPage";
import LoginPage from "./pages/LoginPage";
import SignUpPage from "./pages/signUp/SignUpPage";
import "./css/App.css";
import FindAccountPage from "./pages/FindAccountPage";
import TestPage from "./pages/test/TestPage";

const NotFound = () => {
    return(
        <>
            <h1> NOT FOUND PAGE </h1>
        </>
    )
}

const App = () => {
    return(
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<MainPage/>}/>
                <Route path="/login" element={<LoginPage/>}/>
                <Route path="/signUp" element={<SignUpPage/>}/>
                <Route path="/findAccount" element={<FindAccountPage/>}/>
                <Route path="/test" element={<TestPage/>}/>
                <Route path="*" element={<NotFound/>}/>
            </Routes>
        </BrowserRouter>

    )
}

export default App;
