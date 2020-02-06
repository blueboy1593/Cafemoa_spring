import React, { Component } from 'react'
import { Navbar, Nav } from 'react-bootstrap';
import { Link } from 'react-router-dom';

export default class NavbarVisitor extends Component {
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
            <Link to='/visitor'><img src="/img/cafeinfo.png" alt="카페정보" /></Link>
            <Link to='/visitor/nearcafe'><img src="/img/nearcafe.png" alt="내 주변 카페" /></Link>
          </Nav>
          <Nav>
            <Link to='/visitor/login'><img src="/img/login.png" alt="로그인" /></Link>
            <Link to='/visitor/signup'><img src="/img/signup.png" alt="회원가입" /></Link>
            {/* <Nav.Link><img src="/login.png" alt="" /></Nav.Link>
            <Nav.Link><img src="/signup.png" alt="" /></Nav.Link> */}
          </Nav>
        </Navbar.Collapse>
      </Navbar>
      </div>
    )
  }
}
