import React,{Component} from "react";
import CropService from "../services/CropService";

class ViewCropComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            crop: []
        }
    }
    

    componentDidMount(){
        
        CropService.getCropById(this.state.id).then( res => {
            this.setState({crop: res.data});
        })
    }

    addToCart(id){
        this.props.history.push(`/add-cart/${id}`);
    }
    

    render() {
        return (
            <div>
                <br></br>
                <div className = "card col-md-6 offset-md-3" style={{background:"#FCF4A3",borderRadius:"30px"}}>
                    <h3 className = "text-center" style={{color:"black"}}> View Crop Details</h3>
                    
                    <div className = "card-body" style={{color:"black"}}>
                        <div className = "row">
                            <center><strong>CropName :</strong>{ this.state.crop.cropName }</center>
                        </div>
                        <div className = "row">
                            <center><strong>Crop Type :</strong> { this.state.crop.cropType }</center>
                        </div>
                        <div className = "row">
                            <center><strong> Quantity :</strong> { this.state.crop.quantity }</center>
                        </div>
                        <div className = "row">
                            <center><strong>Price : </strong>{ this.state.crop.price }</center>
                        </div>
                        <div className = "row">
                            <center> <strong>Address :</strong> { this.state.crop.address }</center>
                        </div>
                        <br></br><br></br>
                        <center><button style={{marginLeft: "10px"}} className="btn btn-success" onClick={()=>this.addToCart(this.state.crop.id)}>Add to cart</button></center>
                    </div>
                    
                </div>
            </div>
        
           
        )
        
    }
   
}

export default ViewCropComponent;
