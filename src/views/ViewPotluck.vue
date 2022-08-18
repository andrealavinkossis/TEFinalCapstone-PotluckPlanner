<template>
<div>
 <div class="card" mb-5>
  
  <div class="card-content">
    <header class="card-header">
      <p class="card-header-title is-size-1">
        {{potluck.potluck_name}}
      </p>
    </header>  
  </div>

  <div class="card-content">
    <p class="title is-4">Description</p>
    <p class="subtitle is-6"> {{potluck.description }}</p>
  </div>

  <div class="card-content">
    <p class="title is-4">Date and Time</p>
    <p class="subtitle is-6">{{potluck.date_time }}</p>
  </div>

  <div class="card-content">
    <p class="title is-4">Host</p>
    <p class="subtitle is-6"> {{user.username }}</p>
  </div>

  <div class="card-content">
    <p class="title is-4">Courses Needed</p> <!-- need a for loop here -->
    <p class="subtitle is-6" v-for="c in course" v-bind:key="c.course_id">
      <span class="is-size-6"><span class="has-text-weight-medium">{{c.course_name}}:</span> The potluck needs at least {{c.amount_needed}}.</span> 
      </p>
  </div>

   <div class="card-content">
    <p class="title is-4">Dishes</p>  <!-- need a for loop here -->
    <p class="subtitle is-6" v-for="d in dish" v-bind:key="d.dish_id"> {{d.dish_name}} ({{d.restrictions}}) </p>
  </div>

  <div class="card-content">
    <p class="title is-4">Guests</p> <!-- need a for loop here -->
    <p class="subtitle is-6" v-for="g in guest" v-bind:key="g.guest_id">{{g.username}}</p>
  </div>

  <div class="card-content"> <!-- need a for loop here and get all guests by potluck id tied to users for the restrictions-->
    <p class="title is-4">Dietary Restrictions of Guests</p>
    <p class="subtitle is-6">{{user.restrictions}}</p> <!-- this is wrong-->
  </div>
    
<div class="card">
  <footer class="card-footer">
    <a class="card-footer-item" v-on:click="togglePotluckForm()">Update Potluck</a>
     <a class="card-footer-item" v-on:click="toggleCourseForm()">Update Courses</a>
      <a class="card-footer-item" v-on:click="toggleDishForm()">Add Dish</a>
    <a class="card-footer-item" v-on:click="toggleGuestForm()">Edit Guests</a>
    
  </footer>
  
</div>
  

 </div>
  
  <div class="ViewPotluck"> 

    <form id="potluckForm" class="is-hidden">
      <div class="field">
        <label class="label">Potluck Name</label>
          <div class="control">
            <input class="input" v-model="potluck.potluck_name" maxlength="50" type="text" placeholder="get" >
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
          <button class="button" type="button" id="potluckBtn" v-on:click="updatePotluck()"> 
          Update Potluck 
          </button>

          
    </form>
  

    <form id="dishForm" class="is-hidden">
            
  
    <div class="select is-small">
    <select id="food" v-bind:key="coursePicked">

        <option>---- Select Course Type ----</option>
        <option value="Appetizers">Appetizer</option>
        <option value="Main Course">Main Course</option>
        <option value="Desserts">Dessert</option>
        <option value="Beverages">Beverages</option>
     </select>
    </div>
   
    
    <button class="button" type="button" v-on:click="getCourseIds(), toggleDishPartForm()" >Select Course Type</button>
    
 


      <section class="is-hidden" id="dishPartForm">
      <div class="field">
        <label class="label">Dish Name</label>
          <div class="control" >
            <input class="input" v-model="newDish.dish_name" maxlength="50" type="text" placeholder="Dish Name" >
          </div>
        <p class="help">Enter Dish Name here. Limit 50 characters.</p>
      </div>
         
      <div class="field">
        <label class="label">Dish Recipe</label>
          <div class="control">
            <textarea class="textarea" maxlength="1000" type="textarea" placeholder="Text please." v-model="newDish.recipe" >
            </textarea> 
          </div>
        <p class="help">Enter Recipe here. Limit 1000 characters.</p>
      </div>
         
          <div class="field">
            <label class="label">Number of Servings</label>
              <div class="control">
                <input class="input" maxlength="50" type="Number"  v-model="newDish.num_of_servings" >
              </div>
            <p class="help">Enter Number of Servings here.</p>
          </div>

           <div class="field">
            <label class="label">Notes</label>
              <div class="control">
                <input class="input" maxlength="500" type="Text"  v-model="newDish.notes" >
              </div>
            <p class="help">Enter Notes here.</p>
          </div>
          
  <div id="checkboxes" >
    <h2> Please check food any dietary restrictions you may have. </h2>
    <label class="checkbox">
      <input type="checkbox" value="Vegan"  v-model="newDish.restrictions">
        Vegan 
    </label>
        
    <label class="checkbox">
    <input type="checkbox" value="Vegetarian" v-model="newDish.restrictions">
      Vegetarian 
    </label>  
        
    <label class="checkbox">   
      <input type="checkbox" value="Tree Nut-Free"  v-model="newDish.restrictions">
        Tree Nut-Free 
    </label>  
    <p></p>
    <label class="checkbox">
      <input type="checkbox" value="Gluten-Free "  v-model="newDish.restrictions">
        Gluten-Free 
      </label>  
    
    <label class="checkbox">
      <input type="checkbox" value="Shellfish-Free" v-model="newDish.restrictions">
        Shellfish-Free 
    </label>  
        
    <label class="checkbox" >
    <input type="checkbox" value="Peanut-Free"  v-model="newDish.restrictions">
      Peanut-Free 
    </label>
  </div>
          <button class="button" type="button" id="dishBtn" v-on:click="addDish(), reloadPage()"> 
          Add Dish 
          </button>
      </section>
          
    </form>










               
    <div class="field is-hidden" id="courseForm">
      <h1 class="subtitle">Courses Needed:  <button class="button is-small" type="button" id="courseFormBtn" v-on:click="toggleCourseForm()"> 
          Close Course Form 
          </button></h1>
                    
        <div class="columns"> 
          <div class="column is-half">
           
            <label class="label">Appetizers</label>
              <div class="control">
                <input class="input" type="number" placeholder="Number please." v-model="foodGroups.apps" >
                  <button class="button" type="button" id="courseBtn" v-on:click="updateAppetizers()"> 
          Update Appetizers 
          </button>
              </div>
               
            <label class="label">Main Courses</label>
              <div class="control">
                <input class="input" type="number" placeholder="Number please."  v-model="foodGroups.mainCourse" >
                  <button class="button" type="button" id="courseBtn" v-on:click="updateMainCourses()"> 
          Update Main Courses 
          </button>
              </div>
          </div>
               
          <div class="column is-half">  
            <label class="label">Desserts</label>
              <div class="control">
                <input class="input" type="number" placeholder="Number please."  v-model="foodGroups.desserts">
                  <button class="button" type="button" id="courseBtn" v-on:click="updateDesserts()"> 
          Update Desserts 
          </button>
              </div>
              
            <label class="label">Beverages</label>
              <div class="control">
                <input class="input" type="number" placeholder="Number please."  v-model="foodGroups.bevs">
                  <button class="button" type="button" id="bevBtn" v-on:click="updateBeverages()"> 
          Update Beverages 
          </button>
              </div>
          </div>
          
        </div>
            
    </div>

  <div class="field is-hidden" id="guestForm">
    <label class="label">Guests</label>
      <div class="control">
        <input class="input" maxlength="100" type="text" placeholder="Text please." v-model="newGuest.username">
  </div>
  <p class="help">Enter Guests Emails here.</p>
     <button class="button" type="submit" id="GuestBtn" v-on:click="addGuest(), reloadPage()">
         Add Guest 
     </button>
  </div>

  
  </div>
</div>        
</template>

<script>
import potluckService from '../services/PotluckService'
import userService from '../services/UserService'
import courseService from '../services/CourseService'
import guestService from '../services/GuestService'
import dishService from '../services/DishService'


export default {

    components: {
    
  },
   
name: "ViewPotluck",

data() {
    return {
      newPotluck: {
        potluck_id: 0,
        potluck_name: "",
        description: "",
        date_time: "",
        user_id: "",

      },

      selectedCourseId: "",
      coursePicked: "",


      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
        restrictions: []

      },

      potluck: {
        potluck_id: 0,
        potluck_name: "",
        description: "",
        date_time: "",
        user_id: "",

      },
      course: [{
        course_id: "",
        potluck_id:0,
        course_name:"",
        amount_needed: 0,
        allDishesForCourse: null,
        amountRemaining:0,

        
       } ],
      dish: {
        dish_id : "",
        dish_name: "",
        num_of_servings: 0,
        recipe: "",
        notes: "",
        rating: "",
        guest_id: "",
        course_id: "",
        restrictions: []

      },
      guest: {
        guest_id: "",
        potluck_id: "",
        username: "",
      },

       newGuest: {
        
        potluck_id: "",
        username: "",
      },

      foodGroups: {
        mainCourse: 0,
        apps: 0,
        desserts: 0,
        bevs: 0
      },
        newDish: {
        dish_id : "",
        dish_name: "",
        num_of_servings: 0,
        recipe: "",
        notes: "",
        rating: "",
        guest_id: "",
        course_id: "",
        restrictions: []

      },

    
  };
},

methods: {

reloadPage() {
      window.location.reload();
},

addGuest(){
     potluckService.getPotluckById(this.potluck.potluck_id).then(response => {
        this.newPotluck = response.data;
        this.newGuest.potluck_id = this.newPotluck.potluck_id;
        console.log(this.newGuest);
     guestService.addGuests(this.newGuest).then(response => {
          if(response.status == 201){
          alert("Guest Created.");
          
        }else{
         alert("Not good."); 
        }
        });
     })
    this.toggleGuestForm();
},

getCourseIds(){
   this.coursePicked = document.querySelector("#food").value

  if(this.coursePicked == "Appetizers"){
     this.course.forEach(course =>{
    if(course.course_name === "Appetizers"){
    this.selectedCourseId = course.course_id;

  }
     });

  }else if(this.coursePicked == "Main Course"){
  this.course.forEach(course =>{
    if(course.course_name === "Main Course"){
    this.selectedCourseId = course.course_id;
    }
  });

 
    }else if(this.coursePicked == "Desserts"){
  this.course.forEach(course =>{    
    if(course.course_name === "Desserts"){
    this.selectedCourseId = course.course_id;
    }
  });


  } else if(this.coursePicked == "Beverages"){
  this.course.forEach(course =>{    
    if (course.course_name === "Beverages"){
    this.selectedCourseId = course.course_id;
  }
  });
  }
   
},


  addDish(){
    this.newDish.guest_id = this.$route.params.id;
    this.newDish.course_id = this.selectedCourseId;
    this.newDish.restrictions= this.newDish.restrictions.join();
    dishService.addDish(this.newDish).then(response =>{
    
    if(response.status == 201){
        alert("Dish Added.");
          
        }else{
         alert("We are sorry but something seems to have gone wrong."); 
        }
  });
    this.toggleDishForm();
  },

updatePotluck(){
  potluckService.updatePotluck(this.potluck.potluck_id, this.potluck).then(response =>{
    this.potluck = response.data;
    if(response.status == 200){
        alert("Potluck Updated.");
          
        }else{
         alert("We are sorry but something seems to have gone wrong."); 
        }
  });
    this.togglePotluckForm();

},

updateMainCourses(){
  this.course.forEach(main => {
    if(main.course_name === "Main Course"){
    
  main.amount_needed = this.foodGroups.mainCourse;
  
 courseService.updateCourse(main, this.potluck.potluck_id).then(response =>{
   
    if(response.status == 200){
        alert("Course Updated.");
          
        }else{
         alert("We are sorry but something seems to have gone wrong."); 
        }
  });
    }else{
      return null;
    }
    this.foodGroups.mainCourse = "";
    main = "";
   });

},
updateAppetizers(){
    this.course.forEach(course => {
    if(course.course_name === "Appetizers"){
    
  course.amount_needed = this.foodGroups.apps;
 
 courseService.updateCourse(course, this.potluck.potluck_id).then(response =>{
  
    if(response.status == 200){
        alert("Course Updated.");
          
        }else{
         alert("We are sorry but something seems to have gone wrong."); 
        }
  });
    }else{
      return null;
    }
    this.foodGroups.apps = "";
    course = "";
   });

},
updateDesserts(){
  this.course.forEach(course => {
    if(course.course_name === "Desserts"){
    
  course.amount_needed = this.foodGroups.desserts;
 
 courseService.updateCourse(course, this.potluck.potluck_id).then(response =>{
    
    if(response.status == 200){
        alert("Course Updated.");
          
        }else{
         alert("We are sorry but something seems to have gone wrong."); 
        }
  });
    }else{
      return null;
    }
    this.foodGroups.desserts = "";
    course = "";
   });

},
updateBeverages(){
  this.course.forEach(course => {
    if(course.course_name === "Beverages"){
    
  course.amount_needed = this.foodGroups.bevs;
 
 courseService.updateCourse(course, this.potluck.potluck_id).then(response =>{
    
    if(response.status == 200){
        alert("Course Updated.");
          
        }else{
         alert("We are sorry but something seems to have gone wrong."); 
        }
  });
    }else{
      return null;
    }
    this.foodGroups.bevs = "";
    course = "";
   });

},
 toggleDishPartForm(){
     const formToHide  = document.getElementById("dishPartForm");
     if(formToHide.className.includes("is-hidden")) {
       formToHide.classList.remove("is-hidden");
     }else{
      formToHide.classList.add("is-hidden");
  }
 },

 togglePotluckForm(){
     const formToHide  = document.getElementById("potluckForm");
     if(formToHide.className.includes("is-hidden")) {
       formToHide.classList.remove("is-hidden");
     }else{
      formToHide.classList.add("is-hidden");
  }
 },

  toggleCourseForm() {
     const formToHide  = document.getElementById("courseForm");
     if(formToHide.className.includes("is-hidden")) {
       formToHide.classList.remove("is-hidden");
     }else{
      formToHide.classList.add("is-hidden");
  }
  },
    toggleDishForm() {
     const formToHide  = document.getElementById("dishForm");
     if(formToHide.className.includes("is-hidden")) {
       formToHide.classList.remove("is-hidden");
     }else{
      formToHide.classList.add("is-hidden");
  }
  },
    toggleGuestForm() {
     const formToHide  = document.getElementById("guestForm");
     if(formToHide.className.includes("is-hidden")) {
       formToHide.classList.remove("is-hidden");
     }else{
      formToHide.classList.add("is-hidden");
  }
  }

},

created() {
      userService.getUserID(this.$store.state.user.username).then(data =>{
        this.potluck.user_id = data.data 

      potluckService.getPotluckById(this.$route.params.id).then(response => {
        this.potluck = response.data 
        
      courseService.getCourses(this.potluck.potluck_id).then(response => {
        this.course = response.data 
             
      guestService.getGuests(this.potluck.potluck_id).then(response => {
        this.guest = response.data;
       
       userService.getUser(this.potluck.user_id).then(user=>{
          this.user= user.data;

         dishService.getDishes(this.potluck.potluck_id).then(response => {
        this.dish = response.data;  
        
        })  
        })
        })
        })
        })
        });     

},

};
</script>

<style>
#viewCard {
    border: 1px solid black;
    margin-bottom: 100px;
}

</style>