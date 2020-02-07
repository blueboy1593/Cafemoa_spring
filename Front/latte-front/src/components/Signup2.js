import React, { Component } from 'react';
import store from '../store';
import { Button, notification } from 'antd';
import 'antd/dist/antd.css';


class Signup2 extends Component {
  state = {
    id: '',
    pass: '',
    role:'',

  }
  handleChange = (e) => {
    this.setState({
      [e.target.name]: e.target.value
    })
    // console.log([e.target.name])
  }
  handleSubmit = (e) => {
    // 페이지 리로딩 방지
    e.preventDefault();
    store.dispatch({type:'SIGNUP', info:this.state});
    // 상태 초기화
    this.setState({
        id: '',
        pass: '',
        role: '',

    })
  }
  
  
//   componentDidMount{

//   }
// constructor(props){
//     super(props);
//     console.log("constructor");
// }

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
      <h1>회원가입 페이지</h1>


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
          placeholder="손님인가요? 사장인가요?"
          value={this.state.role}
          onChange={this.handleChange}
          name="role"
        />
        <br></br>

        <Button type="submit" onClick={openNotification} >확인</Button>
        {/* <div>{this.state.user_id} {this.state.password}</div> */}
    </form>
    </div>
    );
  }
}

export default Signup2;

// import React, { Component } from 'react';

// class Signup2 extends Component {
//   state = {
//     name: '',
//     phone: ''
//   }
//   handleChange = (e) => {
//     this.setState({
//       [e.target.name]: e.target.value
//     })
//   }
//   handleSubmit = (e) => {
//     // 페이지 리로딩 방지
//     e.preventDefault();
//     // 상태값을 onCreate 를 통하여 부모에게 전달
//     this.props.onCreate(this.state);
//     // 상태 초기화
//     this.setState({
//       name: '',
//       phone: ''
//     })
//   }
//   render() {
//     return (
//       <form onSubmit={this.handleSubmit}>
//         <input
//           placeholder="이름"
//           value={this.state.name}
//           onChange={this.handleChange}
//           name="name"
//         />
//         <input
//           placeholder="전화번호"
//           value={this.state.phone}
//           onChange={this.handleChange}
//           name="phone"
//         />
//         <button type="submit">등록</button>
//       </form>
//     );
//   }
// }

// export default Signup2;