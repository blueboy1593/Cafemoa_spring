import React, { Component } from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import HeaderOwner from '../headers/HeaderOwner'
import { Main, RegisterCafe, RegisterMenu } from '../tabs';

class Customer extends Component{
    render(){
      return (
        <Router>
          <div className="Customer">
            <HeaderOwner></HeaderOwner>
            <Route exact path='/owner' component={Main}/>
            <Route path='/owner/registerCafe' component={RegisterCafe}/>
            <Route path='/owner/registerMenu' component={RegisterMenu}/>
          </div>
        </Router>
      );
    }
  }
  
  export default Customer;
  