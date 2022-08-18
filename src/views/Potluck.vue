<template>
<div>

    <div class="potluck">
        <div v-for="potlucks in potluck" v-bind:key="potlucks.id">
        <router-link :to="`potluck/user/${potlucks.potluck_id}`">
       
        
    <div class="card" id="potluckCard">
  
  <div class="card-content">
    <header class="card-header" id="potluckCardHeader">
      <p class="card-header-title is-size-1">
        {{potlucks.potluck_name}}
      </p>
    </header>  
  </div>

  <div class="card-content">
    <p class="title is-4">Description</p>
    <p class="subtitle is-6"> {{potlucks.description }}</p>
  </div>

  <div class="card-content">
    <p class="title is-4">Date and Time</p>
    <p class="subtitle is-6">{{potlucks.date_time }}</p>
  </div>

  <div class="card-content">
    <p class="title is-4">Host ID</p>
    <p class="subtitle is-6"> {{potlucks.user_id }}</p>
  </div>
    </div>    
       
        </router-link>
     </div>
</div>      
</div>
</template>

<script>
import potluckService from '../services/PotluckService'
import userService from '../services/UserService'

export default {
name:"potluck",

 data() {
    return {
        potluck: [
         {
        potluck_id: "",
        potluck_name: "",
        date_time: "",
        user_id: "",
        description: ""
        } ]
 };
},
methods: {
   
    },
    created() {
        userService.getUserID(this.$store.state.user.username).then(data =>{
        this.potluck.user_id = data.data
        potluckService.getPotluck(this.potluck.user_id).then(response => {
        this.potluck = response.data;
        });
          })     
}
}


</script>

<style>


#potluckCard{
  color: black;
  border: 1px solid black;
  background: rgb(0, 217, 255);
  margin-top: 25px;
  margin-bottom: 25px;
  border-width: 5px;
  border-radius: 10px;
}

#potluckCardHeader {
  font-family: cursive;
  color: rgb(255, 166, 0);
  background: rgb(235, 13, 187);
  border: 1px solid black;
  }

</style>