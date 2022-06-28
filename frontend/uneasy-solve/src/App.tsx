import React, {ChangeEvent, useState} from 'react';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import MainPage from "./MainPage";
import LoginPage from "./LoginPage";
import SignUpPage from "./SignUpPage";
import "./css/App.css";
import FindAccountPage from "./FindAccountPage";

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
                <Route path="*" element={<NotFound/>}/>
            </Routes>
        </BrowserRouter>

    )
}

export default App;
