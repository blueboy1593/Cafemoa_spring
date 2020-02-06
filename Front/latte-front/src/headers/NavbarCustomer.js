import React, { Component } from 'react'
import { Navbar, Nav } from 'react-bootstrap';
import { Link } from 'react-router-dom';

export default class NavbarCustomer extends Component {
  render() {
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
            <Link to='/customer'><img src="/img/cafeinfo.png" alt="카페정보" /></Link>
            <Link to='/customer/order'><img src="/img/order.png" alt="주문하기" /></Link>
            <Link to='/customer/shoppinglist'><img src="/img/history.png" alt="장바구니" /></Link>
          </Nav>
          <Nav>
            <Link to='/customer/mypage'><img src="/img/mypage.png" alt="마이페이지" /></Link>
            <Link to='/customer/mypage'><img src="/img/logout.png" alt="로그아웃" /></Link>
          </Nav>
        </Navbar.Collapse>
      </Navbar>
      </div>
    )
  }
}
