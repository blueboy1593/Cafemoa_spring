import React, { Component } from 'react'
import { Route } from 'react-router-dom'
import { SignupBasic, SignupHost } from '../components/index'

export default class Signup extends Component {
  render() {
    return (
      <div>
        <Route exact path='/visitor/signup' component={SignupBasic}/>
        <Route path='/visitor/signup/host' component={SignupHost}/>
      </div>
    )
  }
}
