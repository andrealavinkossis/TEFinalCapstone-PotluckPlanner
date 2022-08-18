package com.techelevator.controller;

import com.techelevator.dao.CourseDao;
import com.techelevator.dao.DishDao;
import com.techelevator.model.Course;
import com.techelevator.model.Dish;
import com.techelevator.model.PotluckNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class CourseController {

    private CourseDao courseDao;
    private DishDao dishDao;
    public CourseController (CourseDao courseDao) { this.courseDao = courseDao; this.dishDao = dishDao; }

    //list all courses by potluck --> POTENTIAL ENDPOING "/potluck/{potluckId}/course
    @RequestMapping(value = "course/{potluckId}", method = RequestMethod.GET)
    public List<Course> getCoursesByPotluck(@Valid @PathVariable int potluckId) {
        return courseDao.getCoursesByPotluck(potluckId);
    }

    //add a course
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/course", method = RequestMethod.POST)
    public void addCourse(@Valid @RequestBody Course newCourse) {
        try {
            courseDao.addCourse(newCourse);
        } catch (PotluckNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Potluck Not Found!");
        }

    }

    // remove Course from potluck
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @DeleteMapping(value = "/course/{courseId}")
    public void removeCourse(@Valid @PathVariable int courseId){}


    //update course
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/course/{potluckId}", method = RequestMethod.PUT)
    public Course updateCourse(@Valid @RequestBody Course course, @PathVariable int potluckId)
    { return courseDao.updateCourse(course, potluckId);}

    // test out pull dishes by courseId
    @RequestMapping(value = "course/dish/{courseId}", method = RequestMethod.GET)
    public List<Dish> allDishesForCourse(@Valid @PathVariable int courseId) {
        return dishDao.allDishesForCourse(courseId);
    }

    }






