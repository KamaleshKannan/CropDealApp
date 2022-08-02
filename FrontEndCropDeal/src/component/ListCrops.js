import React, { Component } from 'react'
import CropService from "../services/CropService";
import AuthService from '../services/AuthService';
class ListCrops extends Component {
    constructor(props) {
        super(props)

        this.state = {
            currentUser:AuthService.getCurrentUser(),
                crop: []
        }
        this.addCrop = this.addCrop.bind(this);
        this.editCrop = this.editCrop.bind(this);
        this.deleteCrop = this.deleteCrop.bind(this);
    }

    deleteCrop(id){
        CropService.deleteCrop(id).then( res => {
            this.setState({crop: this.state.crop.filter(crop => crop.id !== id)});
        });
    }
    viewCrop(id){
        this.props.history.push(`/view-crop/${id}`);
    }
    editCrop(id){
        this.props.history.push(`/update-crop/${id}`);
    }

    componentDidMount(){
        CropService.getCropByFarmerId(this.state.currentUser.id).then((res) => {
            this.setState({ crop: res.data});
        }).catch((err)=>{console.log(err)});
    }

    addCrop(){
        this.props.history.push('/add-crop/_add');
    }

    render() {
        return (

                 
                 <div className = "row">
                     <h2 style={{paddingTop:"10px"}}><center>FARMER CROPS</center></h2>
                       {/*} <table className = "table table-striped table-bordered">*/}
                       <table className = "table" >
                            <thead style={{background:"black"}}>
                                <tr style={{color:'white'}}>
                                    <th> CROPTYPE</th>
                                    <th> CROPNAME</th>
                                    <th> QUANTITY</th>
                                    <th> PRICE</th>
                                    <th>ADDRESS</th>
                                    <th > ACTIONS</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.crop.map(
                                        crop => 
                                        <tr key = {crop.id} style={{color:'black',backgroundColor:"#98BF64"}} >
                                             <td> {crop.cropType} </td>   
                                             <td> {crop.cropName}</td>
                                             <td> {crop.quantity}</td>
                                             <td> {crop.price}</td>
                                             <td>{crop.address}</td>
                                             <td>
                                                 <button onClick={ () => this.editCrop(crop.id)} className="btn btn-secondary">Update Crop </button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.deleteCrop(crop.id)} className="btn btn-danger">Delete Crop</button>
                                               {/*<button style={{marginLeft: "10px"}} onClick={ () => this.viewCrop(crop.id)} className="btn btn-info">View Crop</button>*/}

                                               
                                                
                                             </td>
                                        </tr>
                                    )
                                }
                            </tbody>
                        </table>
                                                               <div>
                                                                <div className = "row">
                                                                <center><button className="btn btn-success" style={{width:"100px"}}  onClick={this.addCrop}> Add Crop</button></center>
                                                              </div>

                 </div>

            </div>
        )
    }
}

export default ListCrops
