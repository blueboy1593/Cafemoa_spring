import React, { Component } from 'react';
import { Route } from 'react-router-dom';
import { CafeList, RegisterCafe, RegisterMenu } from '../tabs';
import LatteNavbar from '../headers/LatteNavbar';

class Owner extends Component{
    render(){
      return (
        <div className="Owner">
          <LatteNavbar></LatteNavbar>
          <Route exact path='/owner' component={CafeList}/>
          <Route path='/owner/registerCafe' component={RegisterCafe}/>
          <Route path='/owner/registerMenu' component={RegisterMenu}/>
        </div>
      );
    }
  }
  
  export default Owner;
  