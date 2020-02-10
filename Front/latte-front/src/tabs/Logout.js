import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import store from '../store';

export default class Logout extends Component {
  render() {
    localStorage.removeItem("login_token");
    store.dispatch({type:'LOGOUT'})
    return (
      <div>
        <h1>로그아웃 되었습니다.</h1>
        <Link to='/' >홈으로 가기</Link>
      </div>
    )
  }
}
