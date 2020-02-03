import React, { Component } from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import HeaderCustomer from '../headers/HeaderCustomer'
import { Main, ShoppingList, Mypage } from '../tabs';

class Customer extends Component{
    render(){
      return (
        <Router>
          <div className="Customer">
            <HeaderCustomer></HeaderCustomer>
            <Route exact path='/customer' component={Main}/>
            <Route path='/customer/shoppingList' component={ShoppingList}/>
            <Route path='/customer/mypage' component={Mypage}/>
          </div>
        </Router>
      );
    }
  }
  
  export default Customer;
  