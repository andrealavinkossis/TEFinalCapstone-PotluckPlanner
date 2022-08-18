package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Dish {
    @JsonProperty("dish_id")
    private int dish_id;
    @JsonProperty("dish_name")
    private String dish_name;
    @JsonProperty("num_of_servings")
    private int num_of_servings;
    @JsonProperty("recipe")
    private String recipe;
    @JsonProperty("notes")
    private String notes;
    @JsonProperty("rating")
    private int rating;
    @JsonProperty("guest_id")
    private int guest_id;
    @JsonProperty("course_id")
    private int courseId;
    @JsonProperty("restrictions")
    private String restrictions;


    public int amountRemaining;
    public int amountNeeded;

    public int getAmountNeeded() {
        return amountNeeded;
    }

    public void setAmountNeeded(int amountNeeded) {
        this.amountNeeded = amountNeeded;
    }

    public int getDish_id() {
        return dish_id;
    }

    public void setDish_id(int dish_id) {
        this.dish_id = dish_id;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public void setNum_of_servings(int num_of_servings) {
        this.num_of_servings = num_of_servings;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setGuest_id(int guest_id) {
        this.guest_id = guest_id;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }

    public String getDish_name() {
        return dish_name;
    }

    public int getNum_of_servings() {
        return num_of_servings;
    }

    public String getRecipe() {
        return recipe;
    }

    public String getNotes() {
        return notes;
    }

    public int getRating() {
        return rating;
    }

    public int getGuest_id() {
        return guest_id;
    }

    public int getCourse_id() {
        return courseId;
    }

    public String getRestrictions() {
        return restrictions;
    }

    public int getAmountRemaining() {
        return amountRemaining;
    }

    public void setAmountRemaining(int amountRemaining) {
        this.amountRemaining = amountRemaining;
    }




//    public Dish(int dish_id, String dish_name, int num_of_servings, String recipe, String notes, int rating, int guest_id, int course_id, String restrictions ) {
//        this.dish_id = dish_id;
//        this.dish_name = dish_name;
//        this.num_of_servings = num_of_servings;
//        this.recipe = recipe;
//        this.notes = notes;
//        this.rating = rating;
//        this.guest_id = guest_id;
//        this.course_id = course_id;
//        this.restrictions = restrictions;
//    }

    public Dish() {

    }




}