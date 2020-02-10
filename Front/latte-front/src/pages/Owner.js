import React, { Component } from 'react';
import { Route } from 'react-router-dom';
// import HeaderOwner from '../headers/HeaderOwner'
import { CafeList, RegisterCafe, RegisterMenu } from '../tabs';

class Owner extends Component{
    render(){
      return (
        <div className="Owner">
          {/* <HeaderOwner></HeaderOwner> */}
          <br></br>
          <br></br>
          <br></br>
          <br></br>
          <br></br>
          <Route exact path='/owner' component={CafeList}/>
          <Route path='/owner/registerCafe' component={RegisterCafe}/>
          <Route path='/owner/registerMenu' component={RegisterMenu}/>
        </div>
      );
    }
  }
  
  export default Owner;
  