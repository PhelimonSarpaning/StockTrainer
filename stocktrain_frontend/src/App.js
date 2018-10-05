import React, { Component } from 'react';
import Main from './container/Main';
import './css/App.css';
import NavBar from './container/NavBar';

import SignUp from './container/SignUp';

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      currentUser: null,
      loginActive: false 
    }
    this.activateLogin = this.activateLogin.bind(this)
  }

  activateLogin(e) {
    e.preventDefault()
    this.setState({
      loginActive : !this.state.loginActive
    })
  }

  LoginActive () {
    if (this.state.loginActive) {
      return(
        <SignUp loginActive={this.activateLogin}/> 
      );
    }
  }
  

  render () {
    return (
      <div className="App">
        <NavBar loginActive={this.activateLogin}/>
        <div className='LoginActions'>
          {this.LoginActive()}
        </div>
        <Main />
      </div>
    );
  }
}

export default App;
