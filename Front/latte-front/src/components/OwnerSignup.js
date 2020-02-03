import React, { Component } from 'react';
import store from '../store';

class OwnerSignup extends Component {
  state = {
    id: '',
    pass: '',
    sajang: true
  }
  handleChange = (e) => {
    this.setState({
      [e.target.name]: e.target.value
    })
  }
  handleSubmit = (e) => {
    // 페이지 리로딩 방지
    e.preventDefault();
    store.dispatch({type:'SIGNUP', info:this.state});
    // 상태 초기화
    this.setState({
      id: '',
      pass: ''
    })
  }
  render() {
    return (
      <div>
        <h1>여기는 사장님 회원가입 페이지</h1>
        <form onSubmit={this.handleSubmit}>
            <input
            type="id"
            placeholder="아이디"
            value={this.state.id}
            onChange={this.handleChange}
            name="id"
            />
            <br></br>
            <input
            type="password"
            placeholder="비밀번호"
            value={this.state.pass}
            onChange={this.handleChange}
            name="pass"
            />
            <br></br>
            <input
            placeholder="이름"
            // value={this.state.name}
            // onChange={this.handleChange}
            name="name"
            />
            <br></br>
            <input
            placeholder="휴대폰번호"
            name="phone_num"
            />
            <br></br>
            <input
            placeholder="이메일"
            name="email"
            />
            <br></br>
            <input
            placeholder="카페 이름"
            name="cafename"
            />
            <br></br>
            <input
            placeholder="카페 위치"
            name="cafelocation"
            />
            <br></br>
            <button type="submit">회원가입</button>
            {/* <div>{this.state.user_id} {this.state.password}</div> */}
        </form>
    </div>
    );
  }
}

export default OwnerSignup;