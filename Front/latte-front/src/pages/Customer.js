import React, { Component } from 'react';
import { Route } from 'react-router-dom';
import { CafeList, ShoppingList, Mypage, Order } from '../tabs';
// import NavbarCustomer from '../headers/NavbarCustomer';

class Customer extends Component{
    render(){
      console.log('손님페이지 접근')
      return (
        <div className="Customer">
          {/* <HeaderCustomer></HeaderCustomer> */}
          {/* <NavbarCustomer></NavbarCustomer> */}
          <Route exact path='/customer' component={CafeList}/>
          <Route path='/customer/order' component={Order}/>
          <Route path='/customer/shoppingList' component={ShoppingList}/>
          <Route path='/customer/mypage' component={Mypage}/>
          </div>
      );
    }
  }
  
  export default Customer;
  