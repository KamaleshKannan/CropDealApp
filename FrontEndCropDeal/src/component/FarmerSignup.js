
import React, { Component } from 'react'
import axios from 'axios'
import AuthService from '../services/AuthService'
export class FarmerSignup extends Component {
   
    constructor(props){
        super(props)

        this.state = {
            userName: '',
            emailId: '',
            password:'',
            isFarmerCreated: false
    }
    }

    userNameHandler=(e) =>{
      //  const userName = document.getElementById("userName").value
        this.setState({
            userName: e.target.value
        })
        console.log("userName: "+e.target.value)
    }
    emailIdHandler=(e) =>{
      //  const  emailId = document.getElementById(" emailId").value
        this.setState({
            emailId: e.target.value
        })
        console.log("emailId: "+e.target.value)
    }

    passwordHandler=(e) => {
        //const  password = document.getElementById("password").value
        this.setState({
            password: e.target.value
        })
        console.log(" password: "+e.target.value)
    }
    
    registerHandler=(event) => {
        event.preventDefault();

    const newFarmer = {
        userName: this.state.userName,
        email: this.state.emailId,
        password:this.state.password,
        roles:["farmer"]
    };

    console.log(newFarmer)
AuthService.register(newFarmer.userName,newFarmer.email,newFarmer.password,newFarmer.roles).then(response => {
           console.log(response);
           window.alert("Your registration is confirmed.");
       })
       .catch(error => console.log(error));

           
           {/*} this.setState({
            userName: '',
            emailId: '',
            password: '',
            isFarmerCreated: true
        });*/}
        
    };
    
    render() {
        return (
            <div className="container">
                <div className="app-wrapper">
                    <div>
               <h1 className="title">Farmer Registration</h1>
               </div>
             
               <div className="name">
                <label > UserName: 
                <input type="text" name="userName" id="userName"
                value={this.state.userName} onChange={this.userNameHandler}></input></label>
                 <br></br> <br></br>

                <label> emailId: 
                <input  type="email" name="emailId" id="emailId"
                value={this.state.emailId} onChange={this.emailIdHandler}></input></label>
                 <br></br> <br></br>

                <label > Password: 
                <input  type="password" name="password" id="password"
                value={this.state.password} onChange={this.passwordHandler}></input></label>
                <br></br> 
                {/* <div className="form-group"> */}
                <label htmlFor="role" style={{color:"white"}}>Choose a Role:</label>
                              <select  name="role" id="role"  value={this.state.roles}  onChange={this.onChangeRole}  validations={[require]}>
                              <option value="NA">Select Role</option>
                                  <option value="ROLE_FARMER">Farmer</option>
                                  {/* <option value="ROLE_ADMIN">Admin</option> */}
                                  <option value="ROLE_DEALER">Dealer</option>
                              </select>
                          {/* </div> */}
                </div>
                    <button onClick={this.registerHandler}>
                        Register
                    </button> 
            </div>
            </div>
        )
    }
}

export default FarmerSignup





