import React, { Component } from 'react';
import { Route } from 'react-router-dom';
import { Owner, Customer, Visitor } from './pages';
import HeaderLogin from './headers/HeaderLogin';
import './App.css';
// import HeaderVisitor from './headers/HeaderVisitor';

class App extends Component{
  render(){
    return (
      // <Router>
      <div className="App">
        <HeaderLogin></HeaderLogin>
        <Route exact path='/' component={Visitor}/>
        <Route path='/visitor' component={Visitor}/>
        <Route path='/customer' component={Customer}/>
        <Route path='/owner' component={Owner}/>
      </div>
      // </Router>
    );
  }
}

export default App;
