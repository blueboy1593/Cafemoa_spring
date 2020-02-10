import React, { Component } from 'react';
import { Route } from 'react-router-dom';
import { Owner, Customer, Visitor } from './pages';
import './App.css';
import FullpageWrapper from './pages/FullpageWrapper';
// import HeaderVisitor from './headers/HeaderVisitor';
// import HeaderLogin from './headers/HeaderLogin'
import LatteNavbar from './headers/LatteNavbar'
import Logout from './tabs/Logout';
// import store from './store';


class App extends Component{

  constructor(props){
    super(props);
    this.state = {
      role: 'visitor',
    }
  }

  // 솔직히 이게 어떤 역할인지 정확하게 알지는 않아.
  
  // componentDidMount(){
  //   console.log('여기는 App componentDidmount')
  //   const user_state = store.getState().user_info
  //   console.log(user_state)
  //   this.setState({
  //     role: user_state.role
  //   })
  //   console.log('렌더링 다시 해야지?')
  // }
  

  render(){
    console.log('여기는 App 렌더링')
    // const urll = process.env.REACT_APP_SERVER_IP
    return (
      <div className="App">
        {/* <HeaderLogin></HeaderLogin> */}
        {/* 나중에 fullpage는 따로 생각하자. */}
        <LatteNavbar></LatteNavbar>
        {/* <Route path='/' component={LatteNavbar}/> */}
        <Route exact path='/' component={FullpageWrapper}/>
        {/* <Route exact path='/' component={Visitor}/> */}
        <Route path='/visitor' component={Visitor}/>
        <Route path='/customer' component={Customer}/>
        <Route path='/owner' component={Owner}/>

        <Route path='/logout' component={Logout}/>
      </div>
    );
  }
}

export default App;
