import React, { Component } from 'react';
import store from '../store';
import { Button, notification } from 'antd';
import 'antd/dist/antd.css';


class CustomerSignup extends Component {
  state = {
    id: '',
    pass: '',
    role:'',
    name:'',
    email:'',
    nickname:'',
    phone:''
  }
  handleChange = (e) => {
    this.setState({
      [e.target.name]: e.target.value
    })
    console.log(this.state)
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
    // 알림
    const openNotification = () => {
      notification.open({
        id : this.state.id,
        message: this.state.id,
        description: "회원가입 축하합니다~",
      });
      // setTimeout(() => {
      //   notification.open({
      //     key,
      //     message: 'New Title',
      //     description: 'New description.',
      //   });
      // }, 1000);
    };
    return (
      <div>
      <h1>손님 회원가입 페이지</h1>
      <form onSubmit={this.handleSubmit} >
        <input
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
         type="name"
          placeholder="이름"
          value={this.state.name}
          onChange={this.handleChange}
          name="name"
        />
        <br></br>
        <input
          placeholder="별명"
          name="nickname"
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
        <Button type="submit" onClick={openNotification} >회원가입</Button>
        {/* <div>{this.state.user_id} {this.state.password}</div> */}
    </form>
    </div>
    );
  }
}

export default CustomerSignup;