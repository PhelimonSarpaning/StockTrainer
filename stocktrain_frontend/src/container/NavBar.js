import React, { Component } from 'react';
import '../css/App.css';
import SignUp from './SignUp';
import { NavLink} from 'react-router-dom'

class NavBar extends Component {
  constructor(props) {
    super(props)
  }
  render () {
    return (
      <nav className='navbar navbar-expand-lg navbar-light bg-light'>
        <a className='navbar-brand' href='/home'>Stock Trainer</a>
        <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span className="navbar-toggler-icon">Home</span>
        </button>
        <div className='navbar-nav mr-auto'>
          <ul className="navbar-nav">
            <li className="nav-item active">
              <button id="LoginTrig" className="btn btn-outline-success my-2 my-sm-0" type="submit" onClick={this.props.loginActive}>Log In</button>
            </li>
            <li className="nav-item">
              <button id="SignUpTrig" className="btn btn-outline-success my-2 my-sm-0" type="submit" onClick={this.props.loginActive}>Sign Up</button>
            </li>
            <li className="nav-item">

            </li>
            <form className="form-inline my-2 my-lg-0">
              <input className="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search"/>>
              <button id="serachButton"className="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
          </ul>
        </div>
      </nav>
    )
  }
}

export default NavBar;