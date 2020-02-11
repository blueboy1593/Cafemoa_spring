import React, { Component } from 'react';
import { Route } from 'react-router-dom';
import { CafeList, Login, Signup, NearCafe, CafeDetail, Order } from '../tabs';
import LatteNavbar from '../headers/LatteNavbar';
import Logout from '../tabs/Logout';
// import FullpageWrapper from './FullpageWrapper';

// import axios from 'axios'

class Visitor extends Component{
    render(){
      // .env.local 파일에서 url 따와가지고 axios 쉽게 사용하는 방법.
      // const SERVER_IP = process.env.REACT_APP_SERVER_IP
      // console.log(process.env.REACT_APP_SERVER_IP)
      // axios.get(`${SERVER_IP}/cafe/all`)
      //   .then(response => console.log(response))

      return (
        <div className="Visitor">
          <LatteNavbar></LatteNavbar>
          {/* exact path!!! 는 정확한 url을 받기 위한 것이다. 명칭이 겹칠 수 있기 때문에 exact path를 써주거나 혹은 이름을 애매하게 하는게 좋다!!! */}
          <Route exact path='/visitor' component={CafeList}/>
          <Route path='/visitor/cafedetail' component={CafeDetail}/>
          <Route path='/visitor/nearcafe' component={NearCafe}/>
          <Route path='/visitor/order' component={Order}/>
          <center>
          <Route path='/visitor/login' component={Login}/>
          </center>
          <Route path='/visitor/signup' component={Signup}/>
          <Route path='/visitor/logout' component={Logout}/>
        </div>
      );
    }
  }
  
  export default Visitor;
  