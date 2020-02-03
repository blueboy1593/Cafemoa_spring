import React, { Component } from 'react';
import { Link } from 'react-router-dom';

class HeaderLogin extends Component {
    render() {
        return (
            <div>
                {/* 일단 없애자 */}
                {/* <ul style={{float: "right"}}> */}
                <ul>
                    <Link to='/visitor'><img src="/img/visitor.png" alt="방문자" /></Link>
                    <Link to='/customer'><img src="/img/customer.png" alt="손님" /></Link>
                    <Link to='/owner'><img src="/img/owner.png" alt="주인" /></Link>
                    <Link to='/visitor/login'><img src="/img/login.png" alt="로그인" /></Link>
                    <Link to='/visitor/signup'><img src="/img/signup.png" alt="회원가입" /></Link>
                    {/* <Link to='/visitor/login'><img src="/img/login.png" alt="로그인" /></Link>
                    <Link to='/visitor/signup'><img src="/img/signup.png" alt="회원가입" /></Link> */}
                </ul>
            </div>
        );
    }
}

export default HeaderLogin;