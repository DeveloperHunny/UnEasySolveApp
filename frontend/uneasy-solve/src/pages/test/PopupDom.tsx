import ReactDOM from 'react-dom';
import {FC, ReactNode} from "react";
import React from 'react';

const PopupDom:FC<{children : ReactNode}> = ({children}) => {
    const el = document.getElementById('popupDOM');
    return ReactDOM.createPortal(children, el!);
}

export default PopupDom
