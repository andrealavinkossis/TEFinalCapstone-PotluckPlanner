 import axios from 'axios';

export default {

  getRecipe(searchTerm) {
    return axios.get(`https://forkify-api.herokuapp.com/api/search?q=${searchTerm}`)
  }
  
} 