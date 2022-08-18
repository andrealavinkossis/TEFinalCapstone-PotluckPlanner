package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class Course {

    @JsonProperty("course_id")
    @Min(value=0)
    private int courseId; // pk

    @JsonProperty("potluck_id")
    @Min(value=1)
    private int potluckId; // fk to "potluck"

    @JsonProperty("amount_needed")
    @Min(value=0)
    public int amountNeeded;

    @JsonProperty("course_name")
    @NotBlank // must be something in here other than spaces
    private String courseName;

    //STORE A LIST OF DISHES in each course --> similar to List All Courses
    @JsonProperty("amountRemaining")
    public int amountRemaining() {
        if (allDishesForCourse == null) {
            return amountNeeded;
        } else {
            int total = allDishesForCourse.size(); //all the dishes associated with the particular courseId
            return (amountNeeded - total);
        }
    }
    @JsonProperty("allDishesForCourse")
    public List<Dish> allDishesForCourse;


    public Course() {
    }
    //ADDED THIS FOR TEST PURPOSES
    public Course(int courseId, int potluckId, int amountNeeded, String courseName) {
        this.courseId = courseId;
        this.potluckId = potluckId;
        this.amountNeeded = amountNeeded;
        this.courseName = courseName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getPotluckId() {
        return potluckId;
    }

    public void setPotluckId(int potluckId) {
        this.potluckId = potluckId;
    }

    public int getAmountNeeded() {
        return amountNeeded;
    }

    public void setAmountNeeded(int amountNeeded) {
        this.amountNeeded = amountNeeded;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


    }


