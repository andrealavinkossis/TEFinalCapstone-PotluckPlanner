import axios from 'axios';

export default {

  getDishes(potluckId) {
    return axios.get(`/dish/${potluckId}`)
  }, 

  getDish() {
    return axios.get('/', )
  }, 

  addDish(dish) {
    return axios.post('/dish', dish)
  }
}