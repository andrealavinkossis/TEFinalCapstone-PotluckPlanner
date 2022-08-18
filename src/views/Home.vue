<template>
  <div class="home">
 
    <button class="button m-3" id="potluckBtn" v-on:click="toggleForm">
      Create Potluck
    </button>
        
    <router-link v-bind:to="{ name: 'potluck' }">
      <button class="button m-3" id="potluckBtn">
          My Potlucks
      </button>
    </router-link>    
    <div class="columns">
    <div class="column is-half">
      <form id="createForm" class="is-hidden">
         <div class="field">
                <label class="label">Potluck Name</label>
                <div class="control">
                    <input class="input" v-model="potluck.potluck_name" maxlength="50" type="text" placeholder="Text please." >
                </div>
                <p class="help">Enter Potluck Name here. Limit 50 characters.</p>
          </div>
         
          <div class="field">
                <label class="label">Potluck Description</label>
                <div class="control">
                    <textarea class="textarea" maxlength="500" type="textarea" placeholder="Text please." v-model="potluck.description" >
                    </textarea> 
                </div>
                <p class="help">Enter Potluck Description here. Limit 500 characters.</p>
          </div>
         
          <div class="field">
                <label class="label">Date and Time</label>
                <div class="control">
                    <input class="input" maxlength="50" type="datetime-local" placeholder="Date and time please." v-model="potluck.date_time" >
                </div>
                <p class="help">Enter Date and Time here.</p>
          </div>
          
          <button class="button" type="button" v-on:click="createPotluck()"> Submit </button>
       </form>

          <!-- <div class="field">
            <h1 class="subtitle">Courses Needed: </h1>
            <div class="columns"> 
              <div class="column is-half">
               
                <label class="label">Appetizers</label>
                <div class="control">
                    <input class="input" type="number" placeholder="Number please." >
                </div>
               
                <label class="label">Main Courses</label>
                <div class="control">
                    <input class="input" type="number" placeholder="Number please." >
                </div>
                
              </div>
     
              <div class="column is-half">  
               
                <label class="label">Desserts</label>
                <div class="control">
                    <input class="input" type="number" placeholder="Number please." >
                </div>
              
                <label class="label">Beverages</label>
                <div class="control">
                    <input class="input" type="number" placeholder="Number please." >
                </div>
             
              </div>
            </div> -->

       
              
          <!-- </div>
          <div class="field">
                <label class="label">Guests</label>
                <div class="control">
                    <input class="input" maxlength="100" type="text" placeholder="Text please." >
                </div>
                <p class="help">Enter Guests Emails here.</p>
          </div> -->

      
    </div>
        
    <div class ="column">
      <figure class="image ">
        <img class="is-rounded" src="https://render.fineartamerica.com/images/rendered/search/print/6.5/8/break/images/artworkimages/medium/3/vaporwave-bbq-lover-grilling-meat-grill-dad-grillmaster-haselshirt.jpg">
      </figure>
    
    </div>
    
    </div>
     

  </div>  
</template>

<script>
import potluckService from '../services/PotluckService'
import userService from '../services/UserService'


export default {
  
  components: {
    
  },
  name: "home",

    data() {
    return {
      // user: {
      //   userId:'',
      //   username: '',
      //   password: '',
      //   confirmPassword: '',
      //   role: 'user',
      // },

      restrictions: {
        vegan: false,
        vegetarian: false,
        treeNutFree: false,
        glutenFree: false,
        shellfishFree: false,
        peanutFree: false,
      },
      potluck: {
        potluck_name: "",
        description: "",
        date_time: "",
        user_id: '',

      },
      courses: {
        potluck:"",
        courseName:"",
        amountNeeded:"",
      }

    };
    },

methods: {
  toggleForm(){
     const formToHide  = document.getElementById("createForm");
     if(formToHide.className.includes("is-hidden")) {
       formToHide.classList.remove("is-hidden");
     }else{
      formToHide.classList.add("is-hidden");
  }
 },

  createPotluck() {
    
      userService.getUserID(this.$store.state.user.username).then(data =>{
        this.potluck.user_id = data.data
        console.log(this.potluck)
        potluckService.addPotluck(this.potluck).then(response => {
          if(response.status == 201){
          alert("Potluck Created.");
          this.potluck = {};
        }else{
         alert("We are sorry but something seems to have gone wrong."); 
        }
        });
          });
        
      }

},
  created() {
     
 
        },


};

</script>
