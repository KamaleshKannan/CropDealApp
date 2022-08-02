import axios from 'axios';

const CROP_API_BASE_URL = "http://localhost:8095/api/crop";
const CROP_API_CROP_URL = "http://localhost:8095/api/addcrop";
const CROP_API_FARMER_URL="http://localhost:8096/api/farmerCrop";
const CROP_API_DEALER_URL="http://localhost:8054/apicart/dealerCart";
const CROP_API_DELETECART_URL="http://localhost:8054/apicart/deletecart";
const CROP_API_GETBYID_URL="http://localhost:8096/api/crop"
class CropService {

    getCrops(){
        return axios.get(CROP_API_GETBYID_URL);
    }

    createCrop(crop){
        return axios.post(CROP_API_GETBYID_URL, crop);
    }

    getCropById(id){
        
        return axios.get(CROP_API_GETBYID_URL + '/' + id);
    }
    getCropByFarmerId(farmerId){
        
        return axios.get(CROP_API_FARMER_URL + '/' +  farmerId);
    }
    getCartByDealerId(dealerId){
        
        return axios.get(CROP_API_DEALER_URL + '/' + dealerId);
    }
    deleteItem(id){
        return axios.delete(CROP_API_DELETECART_URL + '/' + id);
    }

    updateCrop(crop, id){
        
        return axios.put(CROP_API_GETBYID_URL + '/' + id, crop);
    }

    deleteCrop(id){
        return axios.delete(CROP_API_GETBYID_URL + '/' + id);
    }
}

export default new CropService()


