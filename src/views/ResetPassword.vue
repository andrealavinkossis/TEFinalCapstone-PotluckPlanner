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
      <label for="password" class="sr-only">Password</label>
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
      />
      <div>
           <button type="button" v-on:click="register()" class="button is-link m-2"> Reset Password</button>
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
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },

      
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
      returnedId: '',
    };
  },
  methods: {
      findUserName(){
         
      UserService.getUserID(this.user.username).then(response =>{
        this.returnedId = response.data; 
        console.log(this.returnedId)
        
        });
      },

    register() {
       
      UserService.getUserID(this.user.username).then(response =>{
        if(response.data === 0)        
         {
           this.registrationErrors = true;
           this.registrationErrorMsg = 'Username not found.';
         
      
         }else if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
        
     
      } else  {
        
             authService.resetPassword(this.user.username, this.user).then((response) => {
            if (response.status == 201) {
                alert("Password changed.")
              this.$router.push({
                path: '/login',
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
