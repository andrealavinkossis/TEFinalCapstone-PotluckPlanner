package com.techelevator.dao;

import com.techelevator.model.Course;
import com.techelevator.model.PotluckNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;


public class JdbcCourseDaoTests extends BaseDaoTests {
    //private JdbcTemplate jdbcTemplate; //instantiate a jdbcTemplate
    //private Course testCourse; //instantiate a testCourse
    private JdbcCourseDao sut; //instantiate a JdbcCourseDao, system under test

    //set fake data, per Course model, requires a courseId, a potluckId, an amountNeeded, and a courseName.
    private static final Course COURSE_1 = new Course(1, 1, 5, "Appetizer");
    private static final Course COURSE_DESIGNED_TO_FAIL = new Course(5, 9, 1,"Main Course");

    @Before //before every test, create a new JdbcCourseDao called sut that passes in dataSource AND a new course called testCourse
    public void setup() {
        sut = new JdbcCourseDao(new JdbcTemplate(dataSource), new JdbcDishDao(new JdbcTemplate(dataSource)));
    }

    //    public List<Course> getCoursesByPotluck(int potluckId) {
    @Test
    public void getCoursesByPotluck_Returns_Correct_Courses_With_Existing_PotluckId() {
        List<Course> courses = sut.getCoursesByPotluck(3);
        Assert.assertEquals(1, courses.size());
    }

    @Test
    public void getCoursesByPotluck_Fake_Id_Returns_No_Courses() {
        List<Course> courses = sut.getCoursesByPotluck(7);
        Assert.assertEquals(0, courses.size());
    }

    @Test
    public void happy_path_add_course_does_add_a_course() {
        Course newCourse = sut.addCourse(COURSE_1);
        Assert.assertEquals("Appetizer", newCourse.getCourseName());
    }

    @Test(expected = PotluckNotFoundException.class) //This test expects an exception
    public void add_course_faulty_potluck_id_throws_exception() { //this included modification to Model, JdbcDao and Controller
        Course newCourse = sut.addCourse(COURSE_DESIGNED_TO_FAIL);
    }

    @Test //is there ever a case in which a course is removed? May not need to test.
    public void deleted_course_cannot_be_retrieved() {
        sut.removeCourse(1);

        List<Course> retrievedCourse = sut.getCoursesByPotluck(1);
        Assert.assertEquals(2, retrievedCourse.size());
    }


}
