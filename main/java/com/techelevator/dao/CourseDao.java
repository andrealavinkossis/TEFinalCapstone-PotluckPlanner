package com.techelevator.dao;

import com.techelevator.model.Course;
import com.techelevator.model.PotluckNotFoundException;
import java.util.List;

public interface CourseDao {

    List<Course> getCoursesByPotluck(int potluckId) throws PotluckNotFoundException;

    Course addCourse(Course course);
    // can use in Potluck, this will return a boolean indicating True if it worked.
    // Not throwing an exception b/c we control the courses available to add.

    Course updateCourse (Course course, int potluckId); //do we even need this, since the course names are controlled?
    // can use in Dish if someone wants to re-categorize a dish as a different course, this will return a boolean indicating True if it worked.
    // Not throwing an exception b/c we control the courses available to add.

    void removeCourse (int courseId);
    // HOST can use in Potluck if a host wants to remove a dish.
    // Not throwing an exception b/c we control the courses available to add.

    //boolean updateAmountNeeded(int courseId, int amountNeeded);
    // HOST can use in Potluck
    // this could throw an exception if someone enters a negative number, but that could also be controlled for another way.


}
