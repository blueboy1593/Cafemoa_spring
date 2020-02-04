import React, { Component } from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import HeaderCustomer from '../headers/HeaderCustomer'
import { CafeList, ShoppingList, Mypage, Order } from '../tabs';

class Customer extends Component{
    render(){
      return (
        <Router>
          <div className="Customer">
            <HeaderCustomer></HeaderCustomer>
            <Route exact path='/customer' component={CafeList}/>
            <Route path='/customer/order' component={Order}/>
            <Route path='/customer/shoppingList' component={ShoppingList}/>
            <Route path='/customer/mypage' component={Mypage}/>
          </div>
        </Router>
      );
    }
  }
  
  export default Customer;
  