<template>
 <div class="recipes">

    <p class="my-4">
    <strong>Pick an ingredient for some recipe ideas!</strong>
    </p>

    <div class="select is-small">
    <select id="food" v-bind:key="searchTerm">

        <option>---- Select Food ----</option>
        <option value="carrot">carrot</option>
        <option value="broccoli">broccoli</option>
        <option value="corn">corn</option>
        <option value="mushrooms">mushrooms</option>
        <option value="potato">potato</option>
        <option value="pumpkin">pumpkin</option>
        <option value="ribs">ribs</option>
        <option value="pepperoni">pepperoni</option>
        <option value="ham">ham</option>
        <option value="bacon">bacon</option>
        <option value="crab">crab</option>
        <option value="fish">fish</option>
        <option value="pork">pork</option>
        <option value="turkey">turkey</option>
        <option value="beef">beef</option>
        <option value="ice cream">ice cream</option>
        <option value="sushi">sushi</option>
        <option value="pierogi">pierogi</option>
        <option value="donuts">donuts</option>
        <option value="chocolate">chocolate</option>
        <option value="fajitas">fajitas</option>
        <option value="chili">chili</option>
        <option value="chicken">chicken</option>
        <option value="fries">fries</option>
        <option value="chips">chips</option>
        <option value="seafood">seafood</option>
        <option value="tacos">tacos</option>
        <option value="sausage">sausage</option>
        <option value="cake">cake</option>
        <option value="pie">pie</option>
        <option value="hamburger">hamburger</option>
        <option value="tofu">tofu</option>
        <option value="pudding">pudding</option>
        <option value="bbq">bbq</option>
        <option value="steak">steak</option>
        <option value="lobster">lobster</option>
        <option value="popcorn">popcorn</option>
        <option value="pasta">pasta</option>
        <option value="pizza">pizza</option>
        <option value="salad">salad</option>
        <option value="strawberry">strawberry</option>
        <option value="raspberry">raspberry</option>
        <option value="pineapple">pineapple</option>
        <option value="orange">orange</option>
        <option value="lemon">lemon</option>
        <option value="lime">lime</option>
        <option value="grape">grape</option>
        <option value="coconut">coconut</option>
        <option value="cherry">cherry</option>
        <option value="blueberry">blueberry</option>
        <option value="banana">banana</option>
        <option value="apple">apple</option>
        <option value="bean">bean</option>
        <option value="cinnamon">cinnamon</option>
        <option value="tomato">tomato</option>
        <option value="chili">chili</option>
    </select>
    </div>

    <button class="button is-small" @click="getRecipe">Submit</button>
    <div>
            
 <!--   <figure class="image is-128x128">
    <img src= "https://www.gifimages.pics/images/quotes/english/general/gif-for-food-52650-148861.gif">
    </figure>
-->
    </div>
    <body>
        <div id="showRecipes">
            <div v-for="recipe in recipes.recipes" v-bind:key="recipe.id">
           
            <p class="my-6">
            <section>
             <h1><strong>{{ recipe.title }}</strong></h1>  
             <h2><a :href= "recipe.source_url" > Recipe Link </a></h2>
             <img :src="recipe.image_url"/>
            </section>    
             </p>
            </div>
        </div>
    </body>
</div>      
</template>

<script>
import recipeService from '../services/RecipeService';

export default {
name:"recipes",
  data() {
    return {
        recipes: [
             {
            publisher: "",
            title: "",
            source_url: "",
            recipe_id: "",
            image_url: "",
            social_rank: 0,
            publisher_url: ""
            
         } ],
        searchTerm: "",
    };
  },
    methods: {
        getRecipe() {
            this.searchTerm = document.querySelector("#food").value
          recipeService.getRecipe(this.searchTerm).then((response) => {
              this.recipes = response.data
      })
  },
	}
};
</script>

<style>
</style>