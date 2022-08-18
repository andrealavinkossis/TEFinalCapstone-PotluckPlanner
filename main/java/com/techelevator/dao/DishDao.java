package com.techelevator.dao;

import com.techelevator.model.Dish;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

public interface DishDao {

    public int getDish(int dish_id);


    public int updateDish(@Valid Dish dish, int dish_id);

   boolean deleteDish(int potluckId);


    Dish createDish(Dish dish);

    List<Dish> getDishesByPotluckId(int potluck_id);

    List<Dish> allDishesForCourse(int courseId);

    }