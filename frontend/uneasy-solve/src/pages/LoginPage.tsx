import React, {ChangeEvent, useState} from "react";
import {Link, useNavigate} from "react-router-dom";
import styles from "../css/LoginPage.module.css";
import axios from "axios";

const LoginPage = () => {

    const [email, setEmail] = useState<string>();
    const [password, setPassword] = useState<string>();

    const handleEmail = (e : ChangeEvent<HTMLInputElement>) => {setEmail(e.target.value);}
    const handlePW = (e : ChangeEvent<HTMLInputElement>) => {setPassword(e.target.value);}

    const navigate = useNavigate();

    const onClickLoginBtn = async () => {
        console.log( email , password);
        let result = await axios.post("/api/members/login",
            { email : email, password : password}
        )
        console.log(result.data);
        if(result.data.email == null){
            alert("로그인에 실패하셨습니다.");
            //TODO(추후에 로그인 실패 기능 발전시키기)
        }
        else{
            navigate("/");
        }

    }

    const onClickSignUpBtn = () => {
        navigate("/signUp");
    }

    return(
        <div>
            <div className = {styles.container}>
                <h1 className={styles.title}>해결사</h1>

                <input className={styles.inputEmail} type="text" value={email} onChange={handleEmail} placeholder="이메일"/>
                <input className={styles.inputPW} type="password" value={password} onChange={handlePW} placeholder="비밀번호"/>
                <Link to="/findAccount" className={styles.link}>이메일/비밀번호 찾기 ➜</Link>


                <button className={styles.loginBtn} onClick={onClickLoginBtn}>로그인</button>
                <span className={styles.line}></span>
                <button className={styles.signUpBtn} onClick={onClickSignUpBtn}>회원가입</button>
            </div>
        </div>
    )
}

export default LoginPage;
