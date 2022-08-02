import React, { Component } from 'react'
import axios from 'axios';
import {Redirect} from 'react-router-dom';
import CropService from '../services/CropService';
import AuthService from '../services/AuthService';
export class CartComponent extends Component {
    constructor(props){
        super(props)
        this.state={
            currentUser:AuthService.getCurrentUser(),
            
                id: this.props.match.params.id,
                press: 0,                
                crops: [],             
                cropName:"",           
                cropType:"",            
                quantity:"",         
                price:"",
                totalQuantity:"",
                totalPrice:"",
                carts:[]
            }
        
        
    }
    onChangeQuantity= (event) => {
        this.setState({quantityRequired: event.target.value});
    }

    onChangePrice= (event) => {
        this.setState({totalPrice: event.target.value});
    }
    
    confirmCartHandler=(event) => {
        event.preventDefault();
        // alert("Ordered succesfully");

        
    const newCropItem = {
        dealerId:this.state.currentUser.id,
        cropName: this.state.crops.cropName,
        cropType: this.state.crops.cropType, 
        quantity: this.state.crops.quantity,
        price: this.state.crops.price,
        totalQuantity:this.state.press,
        totalPrice: (this.state.press)*(this.state.crops.price)
    };
    axios.post('http://localhost:8054/apicart/addtocart', newCropItem)
    .then(response => this.props.history.push("/view-cart"))
    .catch(error => console.log(error));
}

    componentDidMount(){
        CropService.getCropById(this.state.id).then( res => {
            this.setState({crops: res.data});
        })
    }
    IncrementCropItem = () => {
        this.setState({ press: this.state.press + 1 });
      }
      DecreaseCropItem = () => {
        this.setState({ press: this.state.press - 1 });
      }
      cancelHandler(){
        this.props.history.push('/crop');
        
    }

    render() {
        return (
            <div>
                 <br></br>
                <div className = "card col-md-6 offset-md-3" style={{background:"#D5B85A",borderRadius:"30px"}}>
                    <h3 className = "text-center"> Add To Cart</h3>
                    <div className = "card-body">
                        <div className = "row">
                            <center><label> <strong>Crop Name:</strong>{ this.state.crops.cropName } </label></center>
                        </div>
                        <div className = "row">
                           <center><label> <strong>CropType:</strong>  { this.state.crops.cropType }</label></center>
                        </div> 
                        <div className = "row">
                            <center><label> <strong>Quantity Available:</strong>{ this.state.crops.quantity } </label></center>
                             </div>
                        <div className = "row">
                            <center><label> <strong>Price:</strong>{ this.state.crops.price } </label></center>
                            
                        </div>
                        
                             <div className="row">
                                <center><label><strong>Quantity Required:</strong></label></center>
                                <br></br>
                             <center>
                             <button className="btn btn-danger"style={{width:"30px"}}onClick={this.DecreaseCropItem}>-</button>
                             <button className="btn btn-success"style={{width:"30px"}}onClick={this.IncrementCropItem}>+</button>
                             </center>
                             <center><h2>{ this.state.press }</h2> </center>
                           </div> 
                        <div className="row">
                           <center> <label><strong>Total Price: </strong>{(this.state.press)*(this.state.crops.price)}</label></center>
                                  
                            <br></br><br></br>
                            
                            </div>  
                            <center> 
                            <button className="btn btn-success" onClick={this.confirmCartHandler}>Submit</button> 
                           
                            <button className="btn btn-danger" style={{marginLeft:"10px"}}onClick={this. cancelHandler.bind(this)}>Back</button></center>               
                      
                    </div>

                </div>
                
            </div>
        )
    }
}
export default CartComponent
