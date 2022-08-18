import axios from 'axios';

export default {

  login(user) {
    return axios.post('/login', user)
  },

  register(user) {
    return axios.post('/register', user)
  },
  
  resetPassword(username, user) {
    return axios.put(`/register/${username}`, user)
  },

  editUser(username, user) {
    return axios.put(`/register/user/${username}`, user)
  }

}
