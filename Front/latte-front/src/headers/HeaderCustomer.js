import React, { Component } from 'react';
import { Link } from 'react-router-dom';

const logo_style = {
    opacity: 1,
    display: 'block'
}

class HeaderCustomer extends Component {
    render() {
        return (
            <div className="top-fixed">
                <div id="logo" className="logo" style={logo_style}><Link to='/'><img src="/img/logo.png" width="120px" alt="라떼는말이야" /></Link>
                    <ul className="icon-list" style={{float: "right"}}>
                        <Link to='/customer'><img src="/img/cafeinfo.png" alt="카페정보" /></Link>
                        <Link to='/customer/shoppinglist'><img src="/img/shoppinglist.png" alt="장바구니" /></Link>
                        <Link to='/customer/mypage'><img src="/img/mypage.png" alt="마이페이지" /></Link>
                    </ul>
                </div>
            </div>
        );
    }
}

export default HeaderCustomer;