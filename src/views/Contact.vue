<template>
 <div class="contact">
      <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Hello Bulma!</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
  </head>
  <body>
  <section class="section">
    <div class="container">
      <h1 class="title">
        We would love to hear from you.
      </h1>
    <div class="container my-5 mt-5 mx-5 px-5">
    <h2 class="title is-2 is-capitalized">Contact Us</h2>
    <form action="" method="post">
        <div class="field">
            <label for="name" class="label is-size-4 has-text-weight-light"></label>
            <div class="control has-icons-left">
                <input type="text" name="name" id="name" class="input" placeholder="Name" v-model="contact.contact_name" autofocus>
                    <span class="icon is-left">
                        <i class="fa fa-user"></i>
                    </span>
            </div>
        </div>
        <div class="field">
            <label for="email" class="label is-size-4 has-text-weight-light"></label>
            <div class="control has-icons-left">
                <input type="email" name="email" id="email" class="input" placeholder="Email" v-model="contact.contact_email">
                    <span class="icon is-left">
                        <i class="fa fa-envelope"></i>
                    </span>
            </div>
        </div>
        <div class="field">
            <label for="message" class="label is-size-4 has-text-weight-light"></label>
            <textarea name="message" id="message" rows="5" class="textarea is-medium" placeholder="Message" v-model="contact.contact_message"></textarea>
        </div>
        <button type="button" v-on:click="addContactForm()" class="button is-success is-size-5">Submit</button>
    </form>
</div>
      <!-- <figure class="image">
        <img class="image" src="https://c.tenor.com/R1uik9PL17gAAAAC/stormcastle-hotdog-sandwich-dance.gif">
      </figure> -->
    </div>
  </section>
  </body>
</div>      

</template>

<script>
import ContactService from '../services/ContactService';
export default {



name:"contact",
    data() {
    return {
      contact: {
        contact_name: '',
        contact_email: '',
        contact_message: '',
      
      },

    }
    },

    methods:{
      
      addContactForm() {

        ContactService.addContactForm(this.contact).then(data =>{
        if(data.status == 201){
          alert("Thank you for your message.");
          this.contact = {};
        }else{
         alert("We are sorry but something seems to have gone wrong."); 
        }
        });

      }


    },

};

</script>

<style>

</style>