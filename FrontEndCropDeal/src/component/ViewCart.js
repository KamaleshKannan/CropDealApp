import React, { Component } from 'react'
import axios from 'axios';
import CropService from '../services/CropService';
import AuthService from '../services/AuthService';
import StripeButton from './StripeButton';
export class ViewCart extends Component {
    constructor(props) {
        super(props)

        this.state = {
            currentUser:AuthService.getCurrentUser(),
                cart: []
        }
        this.deleteItem=this.deleteItem.bind(this);
      
    }
   /* componentDidMount(){
        axios.get("http://localhost:8054/apicart/getcart").then((res) => {
            this.setState({ cart: res.data});
        });
    }*/
    componentDidMount(){
        
       CropService.getCartByDealerId(this.state.currentUser.id).then((res) => {
           console.log(res);
            this.setState({ cart: res.data});
           
        });
    }


    deleteItem(id){
        CropService.deleteItem(id).then( res => {
            this.setState({cart: this.state.cart.filter(carts => carts.id !== id)});
        });
}
paymentProcess(id){
    
    this.setState({cart: this.state.cart.filter(cart => cart.id !== id)});
 
}
    render() {
        return (
                <div>
                 <h2 className="text-center" style={{paddingTop:"0px"}}>Cart Crop List</h2>
                 <div className = "row">
                 </div>
                 <br></br>
                 <div className = "row">
                        <table className = "table table-striped table-bordered">

                            <thead style={{background:"black"}}>
                                <tr style={{color:'white'}}>
                                    <th> Crop Name</th>
                                    <th> Crop Type</th>
                                    <th> Quantity</th>
                                    <th> Price Per kg</th> 
                                    <th> Total Quantity</th>
                                    <th>Total Price</th>
                                    <th> Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                 this.state.cart.map(
                                        carts =>
                                        <tr key = {carts.id} style={{color:'black',backgroundColor:"#98BF64"}}>
                                             <td> {carts.cropName} </td>   
                                             <td> {carts.cropType}</td>
                                             <td> {carts.quantity}</td>
                                             <td> {carts.price}</td>
                                             <td> {carts.totalQuantity}</td>
                                             <td>{carts.totalPrice}</td>
                                             <td>
                                                
                                             <StripeButton  style={{marginLeft: "10px",borderRadius:"12px"}}  price={carts.totalPrice} id={carts.id} >Payment</StripeButton>
                                                <button style={{marginLeft:"40px"}}   onClick={()=>this.deleteItem(carts.id)} className="btn btn-danger">delete</button>
                                             </td>
                                        </tr>
                                    )
                                }
                            </tbody>
                        </table>

                 </div>

            </div>
        )
    }
}

export default ViewCart
