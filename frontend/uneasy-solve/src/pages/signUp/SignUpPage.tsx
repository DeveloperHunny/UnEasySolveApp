import React, {ChangeEvent, FC, FormEvent, useEffect, useRef, useState} from "react";
import styles from "../../css/SignUpPage.module.css";
import {Interface} from "readline";
import {useDaumPostcodePopup} from "react-daum-postcode";
import Postcode from "../test/PostCode";

const SignUpPage = () => {

    // === 필수 입력 사항들 ===
    const [email, setEmail] = useState<string>("");
    const [password, setPW] = useState<string>("");
    const [pwCheck, setPWCheck] = useState<string>("");
    const [nickname, setNickName] = useState<string>("");
    const [address, setAddress] = useState<string>("");

    // === 선택 입력 사항들 ===

    const [sex, setSex] = useState<number|null>(null);
    const [age, setAge] = useState<number>();
    const [job, setJob] = useState<string>("");
    const [phone, setPhone] = useState<string>("");


    const jobArr = ["학생", "사무직", "영업직", "IT계열", "교육계열", "공무원", "기술자", "기타"];
    const [check, setCheck] = useState({ 'email' : false, 'pw' : false, 'pwCheck' : false, 'nickname' : false, 'address' : false, 'phone' : true});



    function checkEmail(){
        var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
        if(!reg_email.test(email)) {
            setCheck({ ...check, 'email': false});
        }
        else {
            setCheck({...check, 'email': true});
        }
    }

    function checkPW(){
        // 영어 / 숫자 / 특수 문자 조합 (13자리 이상)
        var reg_pw = /^(?=.*?[A-Z|a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{13,}$/;
        if(!reg_pw.test(password)) {
            setCheck({...check, 'pw' : false});
        }else {
            setCheck({...check, 'pw' : true});
        }
    }

    function checkRePW(){
        if(!(password === pwCheck)){
            setCheck({...check, 'pwCheck' : false});
        }
        else{
            setCheck({...check, 'pwCheck' : true});
        }
    }

    function checkNickname(){
        // 한글 , 영어, 숫자만 가능 (2~10자리)
        var reg_nickname = /^([a-zA-Z0-9ㄱ-ㅎ|ㅏ-ㅣ|가-힣]).{1,10}$/;
        if (!reg_nickname.test(nickname)){
            setCheck({...check, 'nickname' : false});
        }
        else{
            setCheck({...check, 'nickname' : true});
        }
    }

    function checkPhone(){
        var reg_phone = /^01(?:0|1|[6-9])-(?:\d{3}|\d{4})-\d{4}$/;
        if(!reg_phone.test(phone)){
            setCheck({...check, 'phone' : false});
        }
        else{
            setCheck({...check, 'phone' : true});
        }
    }



    const ErrorMsg:FC<{msg : string}> = ({msg}) => {
        return(
            <p style={{ color:"red", marginTop:"10px", marginBottom:"15px"}}>{msg}</p>
        )

    }


    //Address Popup
    const open = useDaumPostcodePopup("//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js");


    const handleComplete = (data : any) => {
        // data.bname & data.buildingName 등등 더 여러 정보 있음. 다른 정보 필요하면 찾아볼 것
        setAddress(data.address);
        setCheck({...check, address: true});
        console.log(address);
    }
    const openPopup = () => {
        open({ popupTitle: '우편번호 검색 팝업', popupKey: 'popup1', onComplete : handleComplete});
    }

    const handleSubmit = (e : FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        //TODO( 서버와 데이터 비교를 통해 로그인 성공 or 실패 진행)
   }


   const validation = useRef(false);

   const formValidate = () => {
       let isValid = true;
       Object.entries(check).forEach((entry, idx) => {
          if(entry[1] === false){isValid = false; return false;}
       });

       validation.current = isValid;
   }

   useEffect(() => {

       formValidate();

   },[check]);





    return(
        <div>
            <form className={styles.container} onSubmit={handleSubmit}>
                <h1 className={styles.title}>회원가입</h1>
                <div className={styles.require} style={{ marginTop : "45px"}}>
                    <span style={{ color: "#EBAE34", fontSize:"18px"}}>회원 정보를 입력해주세요.</span>
                    <input style={{marginTop: "5px"}} type="text" value={email} onChange={(e) => {setEmail(e.target.value)}} onBlur={checkEmail} placeholder="이메일"/>
                    {(email.length != 0 && !check["email"]) && <ErrorMsg msg="올바른 이메일 형식을 입력하세요."/>}

                    <input type="password" value={password} onChange={(e) => {setPW(e.target.value)}} placeholder="비밀번호" onBlur={checkPW}/>
                    {(password.length != 0 && !check["pw"]) && <ErrorMsg msg={"올바른 비밀번호를 입력하세요. 영어 / 숫자 / 특수 문자 조합(13자리 이상)"}/>}

                    <input type="password" value={pwCheck} onChange={(e) => {setPWCheck(e.target.value)}} placeholder="비밀번호 확인" onBlur={checkRePW}/>
                    {(pwCheck.length != 0 && !check["pwCheck"]) && <ErrorMsg msg={"비밀번호가 일치하지 않습니다."}/>}


                    <input type="text" value={nickname} onChange={(e) => {setNickName(e.target.value)}} placeholder="닉네임" onBlur={checkNickname}/>
                    {(nickname.length != 0 && !check["nickname"]) && <ErrorMsg msg={"올바른 닉네임 형식을 입력하세요. 한글 / 영어 / 숫자만 가능 (2~10자리) "}/>}


                    <div style={{ width:"500px", display:"flex"}}>
                        <input type="text" value={address} onChange={(e) => {setAddress(e.target.value)}} placeholder="주소" disabled style={{ flex: 4 , borderEndEndRadius:0, borderStartEndRadius:0, }}/>
                        <button type="button" style={{ marginTop: "20px", paddingLeft: "5px", flex:1, fontSize:"24px", borderStartStartRadius:0, borderEndStartRadius:0}} onClick={openPopup}>검색</button>
                    </div>


                </div>

                <span style={{ color : "#EBAE34", fontSize : "18px", marginTop:"45px", marginRight:"auto"}}>선택 입력 사항</span>
                <div className={styles.line} style={{ marginTop:"5px"}}></div>

                <div className={styles.option}>
                    <div>
                        <div className={styles.radioWrap}>
                            <label htmlFor="man" style={{ color:"#EBAE34", fontSize:"24px", marginLeft:"15px"}}>남자</label>
                            <input type="radio" value="1" id="man" name="sex" onChange={(e) => {setSex(Number(e.target.value));}}/>
                        </div>
                        <div className={styles.radioWrap}>
                            <label htmlFor="woman" style={{ color:"#EBAE34", fontSize:"24px", marginLeft:"15px"}}>여자</label>
                            <input type="radio" value="0" id="woman" name="sex" onChange={(e) => {setSex(Number(e.target.value)); }}/>
                        </div>
                    </div>

                    <input type="number" min="1" max="100" value={age} onChange={(e) => {setAge(Number(e.target.value))}} placeholder="(선택) 나이"/>
                    <select name="job" defaultValue="" onChange={(e) => {setJob(e.target.value);}}>
                        <option value="" disabled>(선택) 직업</option>
                        {jobArr.map((item) =>
                            <option key={item} value={item}>{item}</option>)}
                    </select>
                    <input type="text" value={phone} onChange={(e) => {setPhone(e.target.value);}} placeholder="(선택) 핸드폰 번호" onBlur={checkPhone}/>
                    {(phone.length != 0 && !check["phone"]) && <ErrorMsg msg={"올바른 전화번호 형식을 입력하세요. 000 - 0000 - 000 "}/>}
                </div>


                <button type="submit" style={{ marginTop:"50px"}} disabled={!validation.current} >회원가입</button>

            </form>
        </div>
    )
}

export default SignUpPage;
