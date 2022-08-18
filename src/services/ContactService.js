import axios from 'axios';

export default {

  addContactForm(contact) {
    return axios.post('/contact', contact)
  }, 

 
  

}