import React, { Component } from 'react';
import '../css/App.css';
import NavBar from './NavBar';

class SignUp extends Component {
  constructor(props) {
    super(props)
    this.state = {
      userEmail: null,
      usernameField: null,
      passwordField: null,
      confirmPasswordField: null
    }
    this.amendStateInfo = this.amendStateInfo.bind(this)
    this.processSignUp = this.processSignUp.bind(this)
  }

  userLogin () {

  }

  userSignUp () {
    return (
      <form>
        <div id="signupDiv" className='form-group'>
          <input type="email" className="form-control" id="userEmail" aria-describedby="emailHelp" placeholder="email" onChange={this.amendStateInfo}/>
          <input type="text" className="form-control" id="usernameField" aria-describedby="emailHelp" placeholder="username"/>
          <input type="password" className="form-control" id="passwordField" aria-describedby="emailHelp" placeholder="password"/>
          <input type="password" className="form-control" id="confirmPasswordField" aria-describedby="emailHelp" placeholder="confirm password"/>
          <button type="button" className="btn btn-primary" id="submitSignUp" onClick={this.processSignUp}>Submit</button>
        </div>
      </form>
    )
  }

  processSignUp (event) {
    this.props.loginActive(event)
  }

  amendStateInfo (event) {
    event.preventDefault()
    this.state.userEmail = event.target.value
  }

  render () {
    return(
      <div className='signUpDiv'>
        {this.userSignUp()}
      </div>
    )
  }
}

export default SignUp;