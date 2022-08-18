import axios from 'axios';

export default {

  getPotluck(userId) {
    return axios.get(`/potluck/user/${userId}`)
  }, 

 
  addPotluck(potluck) {
    return axios.post('/potluck', potluck)
  }, 
  
  getPotluckById(potluckId) {
    return axios.get(`/potluck/${potluckId}`)
  },


  updatePotluck(potluckId, potluck) {
    return axios.put(`/potluck/${potluckId}`, potluck)
  }
}
