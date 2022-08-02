import React,{Component} from "react";
import CropService from "../services/CropService";
import "./styles.css";
class ListCropComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
                crops: []
        }
        {/*this.addCrop = this.addCrop.bind(this);*/}
        this.editCrop = this.editCrop.bind(this);
        this.deleteCrop = this.deleteCrop.bind(this);
        this.ViewCart=this.ViewCart.bind(this);
    }

    deleteCrop(id){
        CropService.deleteCrop(id).then( res => {
            this.setState({crops: this.state.crops.filter(crop => crop.id !== id)});
        });
    }
    viewCrop(id){
        this.props.history.push(`/view-crop/${id}`);
    }
    editCrop(id){
        this.props.history.push(`/update-crop/${id}`);
    }

    componentDidMount(){
        CropService.getCrops().then((res) => {
            console.log(res);
            this.setState({ crops: res.data});
        });
    }

    addCrop(){
        this.props.history.push('/add-crop/_add');
    }
    ViewCart(){
        this.props.history.push(`/view-cart`);
       }

    render() {
        return (
            <div>
                 <h2 className="text-center"style={{paddingTop:"0px"}}>Crop List</h2>
                 <div className = "row">
                  {/*  <button className="btn btn-primary" onClick={this.addCrop}> Add Crop</button>*/}
                 </div>
                {/*} <button onClick={ this.ViewCart} className="btn btn-info" style={{"width":"100px"}}>Cart</button>*/}
                 <br></br>
                 <div className = "row">
                        {/*<table className = "table table-striped table-bordered">*/}
                        <table className = "table">
                            <thead style={{background:"black"}}>
                                <tr style={{color:'white'}}>
                                    <th style={{color:"white"}}> cropType</th>
                                    <th style={{color:"white"}}> cropName</th>
                                    <th style={{color:"white"}}> quantity</th>
                                    <th style={{color:"white"}}> price</th>
                                    <th style={{color:"white"}}> Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.crops.map(
                                        crop => 
                                        <tr key = {crop.id} style={{color:'black',backgroundColor:"#98BF64"}}>
                                             <td> {crop.cropType} </td>   
                                             <td> {crop.cropName}</td>
                                             <td> {crop.quantity}</td>
                                             <td> {crop.price}</td>
                                             <td>
                                                {/*} <button onClick={ () => this.editCrop(crop.id)} className="btn btn-info">Update crop </button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.deleteCrop(crop.id)} className="btn btn-danger">Delete crop</button>*/}
                                                <button style={{marginLeft: "10px"}} onClick={ () => this.viewCrop(crop.id)} className="btn btn-secondary">View </button>
                                                
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

export default ListCropComponent;

