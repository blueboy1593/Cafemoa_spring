import React, { Component } from 'react'
import { Toast, ToastBody, ToastHeader } from 'reactstrap';

export default class Mypage extends Component {
    render() {
        return (
            <div>
                <h1>여기는 마이페이지! 여기서 작업하면 됨!</h1>
            <div className="p-3 bg-warning my-2 rounded">
                <Toast>
                <ToastHeader>
                    Mypage
                </ToastHeader>
                <ToastBody>
                    당신의 정보를 가지고 있습니다.
                </ToastBody>
                </Toast>
                
            </div>
            </div>
        )
    }
}
