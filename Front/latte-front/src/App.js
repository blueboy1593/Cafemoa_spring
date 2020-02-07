import React, { Component } from 'react';
import { Route } from 'react-router-dom';
import { Owner, Customer, Visitor } from './pages';
import './App.css';
import FullpageWrapper from './pages/FullpageWrapper';
// import HeaderVisitor from './headers/HeaderVisitor';
// import HeaderLogin from './headers/HeaderLogin'

class App extends Component{
  render(){
    return (
      // <Router>
      <div className="App">
        {/* <HeaderLogin></HeaderLogin> */}
        <Route exact path='/' component={FullpageWrapper}/>
        {/* <Route exact path='/' component={Visitor}/> */}
        <Route path='/visitor' component={Visitor}/>
        <Route path='/customer' component={Customer}/>
        <Route path='/owner' component={Owner}/>
      </div>
      // </Router>
    );
  }
}

export default App;
