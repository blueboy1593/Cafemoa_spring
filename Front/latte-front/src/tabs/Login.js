import React, {Component} from 'react';
import store from '../store';
import { Link } from 'react-router-dom';
import "./Login.css";
import axios from 'axios';

export default class Login extends Component{
    state = {
        id: '',
        pass: ''
    }
    handleChange = (e) => {
        this.setState({
          [e.target.name]: e.target.value
        })
      }
    handleSubmit = (e) => {
        // 페이지 리로딩 방지
        e.preventDefault();
        
        const params = {
            uid: this.state.id,
            upass: this.state.pass
        }
        
        const base_url = process.env.REACT_APP_SERVER_IP
        axios.post(base_url + '/user/signin', params)
        .then(response => {
            console.log('로그인 요청')
            store.dispatch({type:'LOGIN', token:response.data.token})
            
            // 로컬스토리지에 저장하자!!
            // 이 코드로 로컬 스토리지에 저장한 상태이고 다른거 할 거 없음!
            localStorage.setItem(
                "login_token",
                JSON.stringify(response.data.token)
            );
            this.props.history.push('/');
        }) 
        .catch(error => {
            console.log('error')
            console.error(error)
        })
      }
    render(){
        return(
            // <body>
            <div className="login-page">
                <div className="form">
                    <div className="login">
                        <div className="login-header">
                            <h2>로 그 인</h2>
                        </div>
                    </div>
                <form className="login-form" onSubmit={this.handleSubmit}>
                <label> 이 메 일 </label>
                <input type="id"
                    value={this.state.id}
                    placeholder="아이디를 입력하시오"
                    onChange={this.handleChange} 
                    name="id"
                    />
                <label>비 밀 번 호</label>
                <input type="password" 
                        value={this.state.pass} 
                        className="form-control" 
                        placeholder="비밀번호를 입력하시오" 
                        onChange={this.handleChange} 
                         name="pass"/>
                <button>로그인</button>
                <p className="message">아이디가 없으신가요 ? <Link to="#">회원가입하기 </Link></p>
                <div className="forgot-password">
                    비밀번호를 잊어버리셨습니까? <Link to="/">비밀번호 찾기</Link>
                </div>
                </form>
                </div>
            </div>
        )
    }
}
