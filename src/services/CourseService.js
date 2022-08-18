import axios from 'axios';

export default {

  getCourses(potluckId) {
    return axios.get(`/course/${potluckId}`)
  }, 

  updateCourse (course, potluckId) {
    return axios.put(`/course/${potluckId}`, course )
  }, 

}