import React, { Component } from 'react';
import { Route } from 'react-router-dom';
import { Owner, Customer, Visitor } from './pages';
import './App.css';
import FullpageWrapper from './pages/FullpageWrapper';
// import LatteNavbar from './headers/LatteNavbar'

// import store from './store';


class App extends Component{
  constructor(props){
    super(props);
    this.state = {
      role: 'visitor',
    }
  }

  render(){
    // console.log('여기는 App 렌더링')
    // const urll = process.env.REACT_APP_SERVER_IP
    return (
      <div className="App">
        {/* <Route path='/' component={LatteNavbar}/> */}
        <Route exact path='/' component={FullpageWrapper}/>
        {/* <Route exact path='/' component={Visitor}/> */}
        <Route path='/visitor' component={Visitor}/>
        <Route path='/customer' component={Customer}/>
        <Route path='/owner' component={Owner}/>
        
      </div>
    );
  }
}

export default App;
