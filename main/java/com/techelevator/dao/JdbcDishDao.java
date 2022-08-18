package com.techelevator.dao;

import com.techelevator.model.Dish;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcDishDao implements DishDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcDishDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private DishDao DishDao;
    private Dish dish;

//Get Dish
    @Override
    public int getDish(int dish_id) {
        String sql = "SELECT dish_id, dish_name, num_of_servings, recipe, notes, rating, guest_id, course_id, restrictions FROM dish WHERE " +
                "dish_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, dish_id);
        if (result.next()) {
            dish = mapRowToDish(result);
        }
        return dish_id;
    }

    //getDishesByCourseId
    @Override
    public List<Dish> allDishesForCourse(int courseId) {
        List<Dish> dishesByCourse = new ArrayList<>();
        String sql = "SELECT * FROM dish WHERE course_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, courseId);
        while (result.next()) {
//            dish = mapRowToDish(result);
            dishesByCourse.add(mapRowToDish(result));
        }
        return dishesByCourse;
    }

//    @Override
//            public List <Dish> getAllDishesByDishId(int dish_id) {
//        List<Dish> dishes = new ArrayList<>();
//    String sql = "SELECT potluck_id  FROM potluck JOIN dish ON potluck.potluck_id=potluck.id" +
//            "WHERE dish_id = ?;";
//    SqlRowSet result = jdbcTemplate.queryForRowSet(sql, dish_id);
//    if(result.next())
//
//    {
//        dish = mapRowToDish(result);
//    }
//    return dishes;
//}

        // potluck_id in potluck to potluck_id in Dish to dish_id"


    //delete dish
    @Override
    public boolean deleteDish(int dish_id) {
        String sql = "SELECT dish_id, dish_name, num_of_servings, recipe, notes, rating, guest_id, course_id, restrictions FROM dish WHERE " +
                "dish_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, dish_id);
        if (result.next()) {
            dish = mapRowToDish(result);
        }
        return true;
    }



    @Override
        public int updateDish( Dish dish, int dish_id) {
            String sql = "UPDATE dish SET dish_name = ?, num_of_servings = ?, recipe = ?, notes = ? rating = ?, guest_id = ?, course_id = ?, restrictions = ? WHERE dish_id = ?;";
//            try {
                jdbcTemplate.update(sql, dish.getDish_name(), dish.getNum_of_servings(), dish.getRecipe(), dish.getNotes(), dish.getRating(), dish.getGuest_id(), dish.getCourse_id(), dish.getRestrictions(), dish_id);
//            } catch (Exception e) {
//            e.printStackTrace();
//            }
            return 1;
        }

     //   get all dishes by potluck_id
  @Override
  public List<Dish> getDishesByPotluckId(int potluck_id) {
        List<Dish> Dishes = new ArrayList<>();
       String sql = "SELECT * FROM dish JOIN course ON dish.course_id = course.course_id WHERE potluck_id = ?;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, potluck_id);
       while (result.next()) {
            Dishes.add(mapRowToDish(result));
        }
       return Dishes;
  }

    @Override
        public Dish createDish(Dish dish) {
            String sql = "INSERT INTO dish (dish_name, num_of_servings, recipe, notes, guest_id, course_id, restrictions) VALUES (?,?,?,?,?,?, ?)" +
                    "RETURNING dish_id;";
            Integer newDishId = jdbcTemplate.queryForObject(sql, Integer.class, dish.getDish_name(), dish.getNum_of_servings(), dish.getRecipe(), dish.getNotes(), dish.getGuest_id(), dish.getCourse_id(), dish.getRestrictions());
dish.setDish_id(newDishId);

            return dish;
        }




    public Dish mapRowToDish(SqlRowSet result) {
        Dish dish = new Dish();
        dish.setDish_id(result.getInt("dish_id"));
        dish.setDish_name(result.getString("dish_name"));
        dish.setNum_of_servings(result.getInt("num_of_servings"));
        dish.setRecipe(result.getString("recipe"));
        dish.setNotes(result.getString("notes"));
        dish.setRating(result.getInt("rating"));
        dish.setGuest_id(result.getInt("guest_id"));
        dish.setCourseId(result.getInt("course_id"));
        dish.setRestrictions(result.getString("restrictions"));
        return dish;

    }
}
