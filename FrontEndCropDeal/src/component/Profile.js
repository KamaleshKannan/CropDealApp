import React, { Component } from "react";
import { Redirect } from "react-router-dom";
import AuthService from "../services/AuthService";

export default class Profile extends Component {
  constructor(props) {
    super(props);

    this.state = {
      redirect: null,
      userReady: false,
      currentUser: { username: "" }
    };
  }

  componentDidMount() {
    const currentUser = AuthService.getCurrentUser();

    if (!currentUser) this.setState({ redirect: "/home" });
    this.setState({ currentUser: currentUser, userReady: true })
  }

  render() {
    if (this.state.redirect) {
      return <Redirect to={this.state.redirect} />
    }

    const { currentUser } = this.state;

    return (
      <div className="container"  style={{color: "black"}}>
        {(this.state.userReady) ?
        <div>
        <header className="jumbotron">
          <h1 ></h1>
         
          <h2><center><strong>PROFILE</strong></center></h2>
          <p>
           <center><strong> UserName:</strong>{" "} {currentUser.username}</center>
          </p>
        </header>
        {/* <p>
          <strong>Token:</strong>{" "}
          {currentUser.accessToken.substring(0, 20)} ...{" "}
          {currentUser.accessToken.substr(currentUser.accessToken.length - 20)}
        </p> */}
        <p>
          <center><strong>Id:</strong>{" "}
          {currentUser.id}</center>
        </p>
        <p>
          <center><strong>Email:</strong>{" "}
          {currentUser.email}</center>
      </p>
        <center><strong>Authorities: </strong>{" "}{currentUser.roles &&currentUser.roles.map((role, index) => <span>{role}</span>)}</center>
          
         
          </div>: null}
          </div>
    );
  }
}