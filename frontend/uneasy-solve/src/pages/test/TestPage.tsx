import axios from 'axios';
import React, {FormEvent, useEffect, useState} from 'react';
import {useNavigate} from "react-router-dom";


const TestPage = () => {

    const [email, setEmail] = useState<string>();
    const [password, setPW] = useState<string>();
    const navigate = useNavigate();

    const handleSubmit = async (e : FormEvent) => {
        e.preventDefault();

        let result = await axios.post("/api/members/login", {
            email : email,
            password : password
        });

        console.log(result.data);

        if(result.data.email == null) alert(" 로그인 실패 !!!");
        else{
            navigate("/")
        }
    }

    return(
        <>
            <form onSubmit={handleSubmit}>
                <input type="text" placeholder="EMAIL" onChange={(e) => {setEmail(e.target.value)}}/>
                <input type="password" placeholder="PASSWORD" onChange={(e) => {setPW(e.target.value)}}/>
                <button type="submit">LOGIN</button>
            </form>
        </>
    )
}

export default TestPage