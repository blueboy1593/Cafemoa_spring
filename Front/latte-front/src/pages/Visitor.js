import React, { Component } from 'react';
import { Route } from 'react-router-dom';
import { CafeList, Login, Signup, LookAround, CustomerCenter, Story } from '../tabs';
import HeaderVisitor from '../headers/HeaderVisitor';
import { CafeDetail } from '../components';

class Visitor extends Component{
    
    render(){
      
      return (
        <div className="Visitor">
          <HeaderVisitor></HeaderVisitor>
          {/* exact path!!! 는 정확한 url을 받기 위한 것이다. 명칭이 겹칠 수 있기 때문에 exact path를 써주거나 혹은 이름을 애매하게 하는게 좋다!!! */}
          <Route exact path='/' component={CafeList}/>
          <Route exact path='/visitor' component={CafeList}/>
          <Route path='/visitor/cafedetail' component={CafeDetail}/>
          <Route path='/visitor/story' component={Story}/>
          <Route path='/visitor/lookAround' component={LookAround}/>
          <Route path='/visitor/customerCenter' component={CustomerCenter}/>
          <center>
          <Route path='/visitor/login' component={Login}/>
          </center>
          <Route path='/visitor/signup' component={Signup}/>
        </div>
      );
    }
  }
  
  export default Visitor;
  