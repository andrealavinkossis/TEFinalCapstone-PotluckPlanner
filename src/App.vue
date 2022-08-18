<template>
  <div id="app">
  
    <div class="navbar" id="navbar">

     
     <div class ="navbar-item" id="logo" >
        <i class="fa-solid fa-hotdog"> </i>
     </div>
    
     <div class="navbar-item">
        <router-link v-bind:to="{ name: 'home' }">
          <button class="button m-2" id="homeBtn">
            Home
          </button>
          </router-link>
          <router-link v-bind:to="{ name: 'recipes' }">
          <button class="button m-2" id="recipesBtn">
            Recipes
          </button>
          </router-link>
          <router-link v-bind:to="{ name: 'contact' }">
          <button class="button m-2" id="contactBtn">
            Contact Us
          </button>
        </router-link>
     
        <h1 id="party" class="title is-uppercase mx-2" > Potluck Party Planner N'At!
        </h1>
      </div> 

     
      <div class="navbar-item navbar-end" id="logoutBtn">
         <router-link v-bind:to="{ name: 'editUser' }" v-if="$store.state.token != ''">
            <button class="button m-2" id="editUserBtn">
              Edit User
             </button>
           </router-link>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">
          <button class="button m-2" id="logoutBtn">
            Logout
          </button>
        </router-link>
      </div>
    
    
    </div>
    <div class="container">      
      <router-view />
    </div>


    <footer class="footer">

        <figure class="image is-128x128">
          
          <img class="image" src="https://c.tenor.com/R1uik9PL17gAAAAC/stormcastle-hotdog-sandwich-dance.gif">
          
        </figure>

    </footer>   
  </div>  
        
</template>

<style>

 

#navbar{
background: rgb(255, 166, 0);
}
#homeBtn{
  color: black;
  border: 1px solid black;
  background: rgb(0, 217, 255);
}
.button{
  color: black;
  border: 1px solid black;
   background: rgb(0, 217, 255);


}
#logo{
  font-size: 100px;
  color: rgb(235, 13, 187);
  -webkit-text-stroke-width: 1px;
  -webkit-text-stroke-color: black;
}

#party {
  font-family: cursive;
  color: rgb(235, 13, 187);
  -webkit-text-stroke-width: 1px;
  -webkit-text-stroke-color: black;
}

.footer {
  background: white;
}



</style>