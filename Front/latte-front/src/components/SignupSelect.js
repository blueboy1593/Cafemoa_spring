import React, { Component } from 'react';
import { Link } from "react-router-dom";

export default class extends Component{
    render(){
        return(
            <div>
                <ul>
                    <button>
                        <Link to="/OwnerSignup">사장님으로 회원가입</Link>
                    </button>
                    <button>
                        <Link to="/CustomerSignup">손님으로 회원가입</Link>
                    </button>
                </ul>
            </div>
        )
    }
}