import axios from 'axios';

export default {

  getGuests(potluckId) {
    return axios.get(`/guest/${potluckId}`)
  }, 

  addGuests(guest) {
    return axios.post('guest/', guest)
  }, 

  removeGuests(guestId) {
    return axios.delete(`/guest/${guestId}`)
  }
}