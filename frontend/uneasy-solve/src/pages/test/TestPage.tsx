// import React, {useState} from "react";
// import PopupPostCode from "./PopupPostCode";
// import PopupDom from "./PopupDom";
//
// const TestPage = () => {
//
//     const [popup, setPopup] = useState(false);
//
//     const openPopup = () => {setPopup(true);}
//
//     const closePopup = () => {setPopup(false);}
//
//     return(
//         <div>
//
//             <button onClick={openPopup}> 우편 번호 검색 </button>
//
//             <div id="popupDom">
//                 {popup &&
//                     <PopupDom>
//                         <PopupPostCode onClose={closePopup} />
//                     </PopupDom>
//                 }
//             </div>
//
//         </div>
//     )
// }
//


import React, {useState} from 'react';
import { useDaumPostcodePopup } from 'react-daum-postcode';

const TestPage = () => {
    const open = useDaumPostcodePopup();
    const [address, setAddress] = useState("주소");

    const handleComplete = (data : any) => {
        console.log(data.address);
        setAddress(data.address);

    };

    const handleClick = () => {
        open({ popupTitle: '우편번호 검색 팝업', popupKey: 'popup' ,onComplete: handleComplete });
    };

    return (
        <>
            <button type='button' onClick={handleClick}>
                Open
            </button>

            <h1> {address}</h1>
        </>

    );
};

export default TestPage;
