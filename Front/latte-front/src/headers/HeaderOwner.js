import React, { Component } from 'react';
import { Link } from 'react-router-dom';

const logo_style = {
    opacity: 1,
    display: 'block'
}

class HeaderOwner extends Component {
    render() {
        return (
            <div className="top-fixed">
                <div id="logo" className="logo" style={logo_style}><Link to='/'><img src="/img/logo.png" width="120px" alt="라떼는말이야" /></Link>
                    <ul className="icon-list" style={{float: "right"}}>
                        <Link to='/owner'><img src="/img/cafeinfo.png" alt="카페정보" /></Link>
                        <Link to='/owner/registercafe'><img src="/img/registercafe.png" alt="카페등록" /></Link>
                        <Link to='/owner/registermenu'><img src="/img/registermenu.png" alt="메뉴등록" /></Link>
                    </ul>
                </div>
            </div>
        );
    }
}

export default HeaderOwner;