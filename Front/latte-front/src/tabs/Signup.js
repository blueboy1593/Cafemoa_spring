import React, { Component } from 'react'
import { BrowserRouter as Router, Route } from 'react-router-dom';
// import OwnerSignup from '../components/OwnerSignup';
// import CustomerSignup from '../components/CustomerSignup';
// import SignupSelect from '../components/SignupSelect';

// 이 코드는 index.js를 통해서 위의 코드와 같은 동작을 하는 코드!!!
import { OwnerSignup, CustomerSignup, SignupSelect } from '../components';

export default class Signup extends Component {
    render() {
        return (
            <div>
                <h1>회원가입 페이지에 오신 것을 환영합니다.</h1>
                <Router>
                    <SignupSelect />
                    <Route path="/OwnerSignup" component={OwnerSignup} />
                    <Route path="/CustomerSignup" component={CustomerSignup} />
                </Router>
            </div>
        )
    }
}