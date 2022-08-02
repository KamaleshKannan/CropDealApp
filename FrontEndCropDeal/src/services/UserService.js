import axios from 'axios';
import authHeader from './authHeader';

const API_URL = 'http://localhost:9095/api/test/';

class UserService {
  getPublicContent() {
    return axios.get(API_URL + 'all');
  }

  getFarmerBoard() {
    return axios.get(API_URL + 'farmer', { headers: authHeader() });
  }

  getDealerBoard() {
    return axios.get(API_URL + 'delear', { headers: authHeader() });
  }

  getAdminBoard() {
    return axios.get(API_URL + 'admin', { headers: authHeader() });
  }
}

export default new UserService();
