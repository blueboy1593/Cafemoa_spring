import React, {Component} from 'react';
import store from '../store';
import { Link } from 'react-router-dom';
import "./Login.css";
import axios from 'axios';

export default class Login extends Component{
    state = {isLoggedIn: store.getState().isLoggedIn,
        id: '',
        pass: ''
    }
    handleChange = (e) => {
        // console.log(e)
        this.setState({
          [e.target.name]: e.target.value
        })
      }
    handleSubmit = (e) => {
        // 페이지 리로딩 방지
        e.preventDefault();
        // const data = {
        //     headers: '',
        //     body: {
        //         userJwtRequestDto:{
        //             uid: this.state.id,
        //             upass: this.state.pass
        //         }
        //     }
        //   }
        
        // const headers = {
        //     Authorization: ''
        // }
        // const data = {
        //     uid: this.state.id,
        //     upass: this.state.pass
        // }
        
        const params = {
            uid: this.state.id,
            upass: this.state.pass
        }
        const headers = {
            Authorization: 'eyJ0eXBlIjoiSldUIiwicmVnRGF0ZSI6MTU4MTA1MDE5ODEzNSwiYWxnIjoiSFMyNTYifQ.eyJtZW1iZXIiOnsidWlkIjoia2FuZ2h5dW4iLCJ1bmFtZSI6Iuq5gOqwle2YhCIsInVwaG9uZSI6IjAxMDExMTEyMjIyIiwidWVtYWlsIjoia2FuZ2h5dW5AbmF2ZXIuY29tIiwidW5pY2tuYW1lIjoia2FuZ2h5dW4iLCJyb2xlIjoiSE9TVCIsInVwaWMiOiLsl4bslrQifX0.AK8zBBxOWnnjvai02JaQiMGMP11gh5BSI4RtK6fE1YA'
        }
        // axios.post(url, params, headers)


        const url = 'http://54.180.154.140:8080/latte/user/signin'

        console.log(url)
        console.log(params)
        axios.post(url, params, headers)
        .then(response => {
            console.log('로그인 요청')
            console.log(response)
        }) 
        .catch(error => {
            console.log('error')
            console.error(error)
        })
        
        // 일단 여기 버리고!
        // store.dispatch({type:'LOGIN', info:this.state});
        // const new_state = store.getState()
        // console.log(new_state)
        // if (new_state) {
        //     return <Link to="/">그러게</Link>
        // }
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
        // </body>
            // <div>
            //     <h1>Login Page</h1>
            //     <form onSubmit={this.handleSubmit}>
            //         <h1>로 그 인</h1>
            //         <div className="form-group">
            //             <label> 이 메 일 </label>
            //             <input type="id" 
            //                 value={this.state.id} 
            //                 className="form-control" 
            //                 placeholder="아이디를 입력하시오" 
            //                 onChange={this.handleChange} 
            //                 name="id"/>
            //         </div>

            //         <div className="form-group">
            //             <label>비 밀 번 호</label>
                        // <input type="password" 
                        //     value={this.state.pass} 
                        //     className="form-control" 
                        //     placeholder="비밀번호를 입력하시오" 
                        //     onChange={this.handleChange} 
                        //     name="pass"/>
            //         </div>

            //         <div className="form-group">
            //             <div className="custom-checkbox">
            //                 <input type="checkbox" className="custom-control-input" id="customCheck1" />
            //                 <label className="custom-control-label" htmlFor="customCheck1">아이디 저장</label>
            //             </div>
            //         </div>

            //         <button type="submit" className="login_btn">Submit</button>
                    // <div className="forgot-password">
                    //     비밀번호를 잊어버리셨습니까? <a href="/">비밀번호 찾기</a>
                    // </div>
            //     </form>
            // </div>
        )
    }
}
