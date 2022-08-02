import React,{Component} from "react";
import AuthService from "../services/AuthService";
import CropService from "../services/CropService";
import './styles.css';
class UpdateCropComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            currentUser:AuthService.getCurrentUser(),
            id: this.props.match.params.id,
            cropType: '',
            cropName: '',
            quantity: '',
            price:'',
            address:''
        }
        this.changeCropTypeHandler = this.changeCropTypeHandler.bind(this);
        this.changeCropNameHandler = this.changeCropNameHandler.bind(this);
        this.changeQuantityHandler=this.changeQuantityHandler.bind(this);
        this.changePriceHandler=this.changePriceHandler.bind(this);
        this.changeAddressHandler =this.changeAddressHandler.bind(this);
        this.updateCrop = this.updateCrop.bind(this);
    }

    componentDidMount(){
        
        CropService.getCropById(this.state.id).then( (res) =>{
            console.table(res);
            let crop = res.data;
            this.setState({
                
                cropName: crop.cropName,
                cropType: crop.cropType,
                quantity : crop.quantity,
                price:crop.price,
                address:crop.address
            });
        });
    }

    updateCrop = (e) => {
        e.preventDefault();
        let crop = {id: this.state.id,cropType: this.state.cropType, cropName: this.state.cropName, quantity: this.state.quantity,price:this.state.price,address:this.state.address,farmerId:this.state.currentUser.id};
        console.log('crop => ' + JSON.stringify(crop));
        console.log('id => ' + JSON.stringify(this.state.id));
        CropService.updateCrop(crop, this.state.id).then( res => {
            this.props.history.push('/crops');
        });
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

    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3" style={{background:"black",borderRadius:"30px"}} >
                                <h3 className="text-center" style={{color:"white"}}>UPDATE CROP</h3>
                                <div className = "card-body">
                                    <form>
                                    <div className = "form-group" style={{color:"white"}}>
                                            <label> CROP TYPE: </label>
                                            <input placeholder="Crop Type" name="cropType" className="form-control" 
                                                value={this.state.cropType} onChange={this.changeCropTypeHandler}/>
                                        </div>
                                        <div className = "form-group" style={{color:"white"}}>
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
                                            <input placeholder="price per kg" name="price" className="form-control" 
                                                value={this.state.price} onChange={this.changePriceHandler}/>
                                        </div>

                                        <div className = "form-group" style={{color:"white"}}>
                                            <label> ADDRESS: </label>
                                            <input placeholder="Address of crop" name="price" className="form-control" 
                                                value={this.state.address} onChange={this.changeAddressHandler}/>
                                        </div>
                                        <br></br><br></br>
                                        <div className="button"><center>
                                        <button className="btn btn-success" onClick={this.updateCrop}>Save</button>
                                        <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button></center>
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

export default UpdateCropComponent;