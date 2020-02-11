import React, { Component } from 'react'
import { Navbar, Nav } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import store from '../store';

// Navbar랑 이름이 중복됨.
export default class LatteNavbar extends Component {
  // state = {
  //   role: store.getState().user_info.role,
  // }  
  render() {
    // const role = this.state.role
    // ㅋㅋㅋ 개고생했네...
    const role = store.getState().user_info.role
    console.log(role, '여기는 Navbar 지역')
    return (
      <div>
        <Navbar bg="fade" expand="lg">
          <Navbar.Brand>
            <Link to='/'>
              <img
                  alt=""
                  src="/img/logo_text.png"
                  className="d-inline-block align-top"
              />{' '}
            </Link>
          </Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="mr-auto">
            {
              (function() {
                if (role === 'HOST') return (
                  <>
                    <Link to='/owner/registercafe'><img src="/img/registercafe.png" alt="카페등록" /></Link>
                    <Link to='/owner/registermenu'><img src="/img/registermenu.png" alt="메뉴등록" /></Link>
                    <Link to='/owner/registermenu'><img src="/img/managemenu.png" alt="메뉴 관리" /></Link>
                    <Link to='/owner/registermenu'><img src="/img/managecafe.png" alt="내 카페 관리" /></Link>
                  </>
                );
                else if (role === 'GUEST') return (
                  <>
                    <Link to='/customer'><img src="/img/cafeinfo.png" alt="카페정보" /></Link>
                    <Link to='/customer/order'><img src="/img/order.png" alt="주문하기" /></Link>
                    <Link to='/customer/shoppinglist'><img src="/img/shoppinglist.png" alt="장바구니" /></Link>
                  </>
                )
                else return (
                  <>
                  <Link to='/visitor'><img src="/img/cafeinfo.png" alt="카페정보" /></Link>
                  <Link to='/visitor/nearcafe'><img src="/img/nearcafe.png" alt="내 주변 카페" /></Link>
                  </>
                );
              })()
            }
          </Nav>
          <Nav>
            {
              (function() {
                if (role === 'HOST' || role === 'GUEST') return (
                  <>
                    {/* <Link to='/visitor/login'><img src="/img/login.png" alt="로그인" /></Link> */}
                    <Link to='/customer/mypage'><img src="/img/mypage.png" alt="마이페이지" /></Link>
                    {/* 여기에는 로그아웃 구현할 것. */}
                    {/* <Link to='/customer/mypage'><img src="/img/logout.png" alt="로그아웃" /></Link> */}
                    <Link to='/visitor/logout'><img src="/img/logout.png" alt="로그아웃" /></Link>
                  </>
                );
                else return (
                  <>
                    <Link to='/visitor/login'><img src="/img/login.png" alt="로그인" /></Link>
                    <Link to='/visitor/signup'><img src="/img/signup.png" alt="회원가입" /></Link>
                  </>
                );
              })()
            }
          </Nav>
        </Navbar.Collapse>
      </Navbar>
      </div>
    )
  }
}
