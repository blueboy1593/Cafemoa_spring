import React, { Component } from 'react'

export default class RegisterCafe extends Component {
    state = {
        cname: '',
        cloc: '',
        cphone: '',
        cpic: '',
        copen: '',
        cclose: '',
        cdesc: '',
      }
    handleChange = (e) => {
    this.setState({
        [e.target.name]: e.target.value
    })
    }
    handleSubmit = (e) => {
    // 페이지 리로딩 방지
    e.preventDefault();
    console.log(this.state)
    // 상태 초기화
    this.setState({
        cname: '',
        cloc: '',
        cphone: '',
        cpic: '',
        copen: '',
        cclose: '',
        cdesc: '',
    })
    }

    render() {
        return (
            <div>
                <h1>여기는 카페 등록 사장페이지 전용!</h1>
                <form onSubmit={this.handleSubmit}>
                    <input
                    type="name"
                    placeholder="카페 이름"
                    value={this.state.cname}
                    onChange={this.handleChange}
                    name="cname"
                    />
                    <br></br>
                    <input
                    type="name"
                    placeholder="카페 위치"
                    value={this.state.cloc}
                    onChange={this.handleChange}
                    name="cloc"
                    />
                    <br></br>
                    <input
                    placeholder="카페 번호"
                    value={this.state.cphone}
                    onChange={this.handleChange}
                    name="cphone"
                    />
                    <br></br>
                    <input
                    placeholder="카페 사진"
                    value={this.state.cpic}
                    onChange={this.handleChange}
                    name="cpic"
                    />
                    <br></br>
                    <input
                    placeholder="카페 오픈 시간"
                    value={this.state.copen}
                    onChange={this.handleChange}
                    name="copen"
                    />
                    <br></br>
                    <input
                    placeholder="카페 닫는 시간"
                    value={this.state.cclose}
                    onChange={this.handleChange}
                    name="cclose"
                    />
                    <br></br>
                    <input
                    placeholder="카페 간략 설명"
                    value={this.state.cdesc}
                    onChange={this.handleChange}
                    name="cdesc"
                    />
                    <br></br>
                    <button type="submit">카페 등록</button>
                </form>
            </div>
        )
    }
}