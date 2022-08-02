import React, { Component } from 'react'
import CropService from '../services/CropService';
import AuthService from '../services/AuthService';
import './styles.css';
class CreateCropComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            currentUser:AuthService.getCurrentUser(),
            id: this.props.match.params.id,
            cropType:'',
            cropName: '',
            quantity: '',
            price:'',
            address:''
        }
        this.changeCropTypeHandler = this.changeCropTypeHandler.bind(this);
        this.changeCropNameHandler = this.changeCropNameHandler.bind(this);
        {/*this.changeQuantityHandler=this.changeQuantityHandler(this);
        this.changePriceHandler=this.changePriceHandler(this);*/}
        this.saveOrUpdateCrop = this.saveOrUpdateCrop.bind(this);
    }

    componentDidMount(){

         if(this.state.id === '_add'){
             return
         }else{
            CropService.getCropById(this.state.id).then( (res) =>{
                let crop = res.data;
                this.setState({
                    cropName: crop.cropName,
                    cropType:crop.cropType,
                    quantity : crop.quantity,
                    price:  crop.price,
                    address: crop.address
                });
            });
        }        
    }
    saveOrUpdateCrop = (e) => {
        e.preventDefault();
        
        let crop = {cropType: this.state.cropType, cropName: this.state.cropName, quantity: this.state.quantity,price:this.state.price,address:this.state.address,farmerId:this.state.currentUser.id};
        console.log('crop => ' + JSON.stringify(crop));

        if(this.state.id === '_add'){
            CropService.createCrop(crop).then(res =>{
                this.props.history.push('/crops');
            });
        }else{
            
            CropService.updateCrop(crop, this.state.id).then( res => {
                this.props.history.push('/crops');
            });
        }
    }
    
    changeCropTypeHandler= (event) => {
        this.setState({cropType: event.target.value});
    }

    changeCropNameHandler= (event) => {
        this.setState({cropName: event.target.value});
    }

   changeQuantityHandler= (event) => {
        this.setState({quantity: event.target.value});
    }
    changePriceHandler=(event)=> {
        this.setState({price:event.target.value});
    }
    changeAddressHandler=(event)=> {
        this.setState({address:event.target.value});
    }
   

    cancel(){
        this.props.history.push('/crops');
    }

    getTitle(){
        if(this.state.id === '_add'){
            return <h3 className="text-center" style={{color:'cornsilk'}}>Add Crop</h3>
        }else{
            return <h3 className="text-center">Update Crop</h3>
        }
    }
    render() {
        return (
            <div>
                <br></br>
                   <div className = "container" >
                        <div className = "row" >
                            <div className = "card col-md-6 offset-md-3 offset-md-3" style={{background:"black",borderRadius:"50px"}}  >
                                {
                                    this.getTitle()
                                }
                                <div className = "card-body" >
                                    <form >
                                        <div className="forms">
                                    <div className = "form-group" style={{color:"white"}} >
                                            <label> FARMER ID: </label>
                                            <input  name="farmerId" className="form-control" 
                                                value={this.state.currentUser.id} disabled={true}/>
                                        </div>
                                        <div className = "form-group" style={{color:"white"}}>
                                            <label> CROPTYPE: </label>
                                            <input required pattern="[a-zA-Z]+" oninvalid="setCustomValidity('Please enter alphabets only. ')"placeholder="Crop Type" name="cropType" className="form-control" 
                                           value={this.state.cropType} onChange={this.changeCropTypeHandler}/>
                                        </div>
                        

                                        <div className="form-group" style={{color:"white"}}>
                                            <label> CROPNAME: </label>
                                            <input placeholder="Crop Name" name="cropName" className="form-control" 
                                                value={this.state.cropName} onChange={this.changeCropNameHandler}/>
                                        </div>
                                        <div className = "form-group" style={{color:"white"}}>
                                            <label> QUANTITY: </label>
                                            <input placeholder="Quantity of crop available" name="quantity" className="form-control" 
                                                value={this.state.quantity} onChange={this.changeQuantityHandler}/>
                                        </div>
                                        <div className = "form-group" style={{color:"white"}}>
                                            <label> PRICE: </label>
                                            <input placeholder="Price per kg" name="price" className="form-control" 
                                                value={this.state.price} onChange={this.changePriceHandler}/>
                                        </div>
                                        <div className = "form-group" style={{color:"white"}}>
                                            <label> ADDRESS: </label>
                                            <input placeholder="Address of farmer" name="price" className="form-control" 
                                                value={this.state.address} onChange={this.changeAddressHandler}/>
                                        </div>
                                        </div>
                                        
                                       <br></br>
                                       <br></br>
   

                                        <div className="button"><center>
                                        <button className="btn btn-success" onClick={this.saveOrUpdateCrop}>Save</button>
                                        
                                        <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Back</button></center>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>

                   </div>
            </div>
        )
    }
}

export default CreateCropComponent;