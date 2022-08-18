package com.techelevator.controller;


import com.techelevator.dao.DishDao;
import com.techelevator.model.Dish;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin

public class DishController {

    private DishDao dishDao;

public DishController(DishDao dishDao) {this.dishDao = dishDao; }

//public class DishController {
//
//    private final com.techelevator.dao.DishDao DishDao;
//    public DishDao dishDao;
//
//    public DishController(DishDao dishDao) {
//        this.DishDao = dishDao;
//    }

//Delete potluck
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/dish/{dish_id}", method = RequestMethod.DELETE)
    public void deleteDish(@Valid @PathVariable int dish_id)  {
    }

    //get all dishes by potluckId
    @RequestMapping(path = "/dish/{potluck_id}", method = RequestMethod.GET)
    public List<Dish> getAllDishesByPotluckId(@Valid @PathVariable int potluck_id) {
        return dishDao.getDishesByPotluckId(potluck_id);
    }

////not sure if needed? get a dish by dishId
//    @RequestMapping(path = "/dish/{dish_id}", method = RequestMethod.GET)
//    public Dish get(@PathVariable int dish_id)  {
//        return dishdao.get(dish_id);
//    }


    //update dish
    @RequestMapping(path = "/dish/{dish_id}", method = RequestMethod.PUT)
    public int updateDish(@Valid @RequestBody Dish dish, @PathVariable int dish_id)  {
        return dishDao.updateDish(dish, dish_id);
    }

    //create a dish
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping( value = "/dish", method = RequestMethod.POST)
    public Dish createDish(@Valid @RequestBody Dish newDish) {
       return dishDao.createDish(newDish);
    }
}
