package com.techelevator.dao;

import com.techelevator.model.Course;
import com.techelevator.model.PotluckNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCourseDao implements CourseDao {

    private JdbcTemplate jdbcTemplate;

    private DishDao dishDao;

    public JdbcCourseDao(JdbcTemplate jdbcTemplate, DishDao dishDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.dishDao = dishDao;
    }

    //ADDED THIS FOR TEST PURPOSES
//    public JdbcCourseDao(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    private Course mapRowToCourse(SqlRowSet rowSet) {
        Course course = new Course();
        course.setCourseId(rowSet.getInt("course_id"));
        course.setCourseName(rowSet.getString("course_name"));
        course.setAmountNeeded(rowSet.getInt("amount_needed"));
        course.setPotluckId(rowSet.getInt("potluck_id"));
        return course;
    }

    @Override
    public List<Course> getCoursesByPotluck(int potluckId) throws PotluckNotFoundException {
        List<Course> allCourses = new ArrayList<>();
        String sql = "SELECT course_id, course_name, amount_needed, potluck_id FROM course WHERE potluck_id = ?;";
        //try {
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, potluckId);
        while (result.next()) {
            Course course = mapRowToCourse(result);
            course.allDishesForCourse = dishDao.allDishesForCourse(course.getCourseId());

            allCourses.add(mapRowToCourse(result));
            //   }
            //} catch (Exception PotluckNotFoundException) {
            //    return null;

        }
        return allCourses;
    }
    //as you get each course result, mapRowToCourse, call DishDao to get all dishes associated w/ that course, THEN add it to AllCourses

    @Override
    public Course addCourse(Course course) throws PotluckNotFoundException { // we want to check to see if the course has been added; if so, true. if not, false.
        try {
            String sql = "INSERT INTO course (potluck_id, amount_needed, course_name) VALUES (?, ?, ?) RETURNING course_id;";
            Integer newCourseId = jdbcTemplate.queryForObject(sql, Integer.class, course.getPotluckId(), course.getAmountNeeded(), course.getCourseName()); //removed long.class - just in case
            course.setCourseId(newCourseId);
            return course;
        } catch (Exception e) {
            throw new PotluckNotFoundException();
        }
    } //NOTE: Added PotluckNotFoundException and try / catch - in case someone tries to add a course to a potluck ID that doesn't exist (even though I don't think you can do that via the front end)

    @Override
    public Course updateCourse(Course course, int potluckId) { // used by a host
        String updateCourseNameSql = "UPDATE course SET amount_needed =? WHERE potluck_id = ? AND course_name=?;";
        jdbcTemplate.update(updateCourseNameSql, course.getAmountNeeded(), potluckId, course.getCourseName() );
        return course;
    }

    @Override // This is never used. Not commenting it out, but we are never going to utilize it.
    public void removeCourse(int courseId) {
        String deleteFromDishSql = "DELETE FROM dish WHERE course_id =?;";
        jdbcTemplate.update(deleteFromDishSql, courseId);

        String deleteFromCourseSql = "DELETE FROM course WHERE course_id = ?;";
        jdbcTemplate.update(deleteFromCourseSql, courseId);

    }
}

        //THE FOLLOWING MAY BE SUPERFLUOUS, MADE ONE METHOD TO UPDATE THEM ALL (ABOVE)
        /*
    @Override
    public boolean updateAmountNeeded(int courseId, int amountNeeded) {
        String updateAmountNeededSql = "UPDATE course SET amount_needed = ? WHERE course_id = ?;";
        int numberOfRowsUpdated = jdbcTemplate.update(updateAmountNeededSql, amountNeeded, courseId);
        if (numberOfRowsUpdated == 1 ) {
            return true;
        } else {
            return false;
        }

    }
         */







