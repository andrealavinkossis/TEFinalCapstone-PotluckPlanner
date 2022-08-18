import axios from 'axios';

export default {

  getUserID(username) {
    return axios.get(`/user/${username}`)
  },
  getUser(id) {
    return axios.get(`/user/id/${id}`)
  },
  
}