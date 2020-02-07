import React, { Component } from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import HeaderOwner from '../headers/HeaderOwner'
import { CafeList, RegisterCafe, RegisterMenu } from '../tabs';

class Owner extends Component{
    render(){
      return (
        <Router>
          <div className="Owner">
            <HeaderOwner></HeaderOwner>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <Route exact path='/owner' component={CafeList}/>
            <Route path='/owner/registerCafe' component={RegisterCafe}/>
            <Route path='/owner/registerMenu' component={RegisterMenu}/>
          </div>
        </Router>
      );
    }
  }
  
  export default Owner;
  