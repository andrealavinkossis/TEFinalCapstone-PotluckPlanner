 <template>
 <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Reset Password</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
     
      <label for="username" class="sr-only">Email Address</label>
      <input
        type="email"
        id="username"
        class="form-control"
        placeholder="Email@email.com"
        v-model="user.username"
        required
        autofocus
      />
     
      <p></p>
      <!-- <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <p></p>
     
      <label for="confirmPassword" class="sr-only">Confirm Password</label>
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      /> -->
       <p></p>
      <div id="checkboxes">
      <h2> Please check food any dietary restrictions you may have. </h2>
      <label class="checkbox">
        <input type="checkbox" value="Vegan"  v-model="user.restrictions">
          Vegan 
        </label>
        <label class="checkbox">
        <input type="checkbox" value="Vegetarian" v-model="user.restrictions">
          Vegetarian 
         </label>  
        <label class="checkbox">   
       <input type="checkbox" value="Tree Nut-Free"  v-model="user.restrictions">
          Tree Nut-Free 
        </label>  
        <p></p>
        <label class="checkbox">
       <input type="checkbox" value="Gluten-Free "  v-model="user.restrictions">
          Gluten-Free 
           </label>  
        <label class="checkbox">
       <input type="checkbox" value="Shellfish-Free" v-model="user.restrictions">
          Shellfish-Free 
           </label>  
        <label class="checkbox" >
        <input type="checkbox" value="Peanut-Free"  v-model="user.restrictions">
          Peanut-Free 
      </label>
      </div>
      <p></p>
      <div>
           <button type="button" v-on:click="updateRestrictions()" class="button is-link m-2"> Update User</button>
           </div>
    </form> 
 </div>  
</template>
<script>
import authService from '../services/AuthService';
import UserService from '../services/UserService';

export default {
  name: 'resetPassword',
  data() {
    return {
     user: {
        username: this.$store.state.user.username,
        password: " ",
        confirmPassword: " ",
        role: 'user',
        restrictions: []
      },

      
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
      returnedId: '',
    };
  },
  created() {
      UserService.getUserID(this.$store.state.user.username).then(data =>{
        this.potluck.user_id = data.data
      });
    UserService.getUser(this.potluck.user_id).then(response =>{
        this.user=response.data
    });

  },
  methods: {
      findUserName(){
         
      UserService.getUserID(this.user.username).then(response =>{
        this.returnedId = response.data; 
        console.log(this.returnedId)
        
        });
      },

    updateRestrictions() {
    this.user.restrictions = this.user.restrictions.join();
      UserService.getUserID(this.user.username).then(response =>{
        if(response.data === 0)        
         {
           this.registrationErrors = true;
           this.registrationErrorMsg = 'Username not found.';
         
      
        //  }else if (this.user.password != this.user.confirmPassword) {
        // this.registrationErrors = true;
        // this.registrationErrorMsg = 'Password & Confirm Password do not match.';
        
     
      } else  {
        
             authService.editUser(this.user.username, this.user).then((response) => {
            if (response.status == 201) {
                alert("User updated.")
              this.$router.push({
                path: '/',
                query:  'Success!' ,
              });   
            }
                           
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
          
      }
      });
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>
