import React, {ChangeEvent, useState} from "react";
import {Link, useNavigate} from "react-router-dom";
import styles from "./css/LoginPage.module.css"

const LoginPage = () => {

    const [email, setEmail] = useState<string>();
    const [password, setPassword] = useState<string>();

    const handleEmail = (e : ChangeEvent<HTMLInputElement>) => {setEmail(email);}
    const handlePW = (e : ChangeEvent<HTMLInputElement>) => {setPassword(password);}

    const navigate = useNavigate();

    const onClickLoginBtn = () => {
        //TODO(로그인 버튼 클릭 시 format 확인 후 -> 로그인 성공 시 메인 페이지로 이동 실패 시 알람 보내기)
        navigate("/");
    }

    const onClickSignUpBtn = () => {
        navigate("/SignUp");
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
