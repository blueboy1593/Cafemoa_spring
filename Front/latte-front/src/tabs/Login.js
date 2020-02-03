import React, {Component} from 'react';
import store from '../store';
import { Link } from 'react-router-dom';
import "./Login.css";

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
        store.dispatch({type:'LOGIN', info:this.state});
        const new_state = store.getState()
        console.log(new_state)
        // if (new_state) {
        //     return <Link to="/">그러게</Link>
        // }
      }
    render(){
        // console.log(this.state)
        return(
            <body>
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
        </body>
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
