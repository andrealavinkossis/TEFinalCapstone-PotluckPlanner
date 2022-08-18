package com.techelevator.dao;

import com.techelevator.model.Guest;
import com.techelevator.model.PotluckNotFoundException;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcGuestDaoTests extends BaseDaoTests {
    private JdbcTemplate jdbcTemplate;
    private Guest testGuest;
    private JdbcGuestDao sut;

    private static final Guest GUEST_DESIGNED_TO_WORK = new Guest(2, 2, "user");
    private static final Guest GUEST_DESIGNED_TO_FAIL = new Guest(3, 8, "user");

    @Before
    public void setup() {
        sut = new JdbcGuestDao(new JdbcTemplate(dataSource));
    }

    @Test
    public void addGuest_works_with_valid_data() {
        Guest newGuest = sut.addGuest(GUEST_DESIGNED_TO_WORK);
        Assert.assertEquals(2, newGuest.getGuestId());
    }

    @Test(expected = PotluckNotFoundException.class)
    public void addGuest_does_not_work_with_invalid_data() {
        Guest newGuest = sut.addGuest(GUEST_DESIGNED_TO_FAIL);
    }

    @Test
    public void getGuestsByPotluck_Returns_Correct_Courses_With_Existing_PotluckId() {
        List<Guest> guests = sut.getGuestsByPotluck(1);
        Assert.assertEquals(1, guests.size());
    }

    //@Test
    //public void getGuestsByPotluck_Fake_PotluckId_Returns_No_Guests(){
    //    List<Guest> guests = sut.getGuestsByPotluck(9);
    //    Assert.assertEquals(0, guests.size());
    //}
    @Test
    public void deleted_guest_will_not_rise_from_the_dead() {
        sut.removeGuest(1);
        List<Guest> retrievedGuests = sut.getGuestsByPotluck(1);
        Assert.assertEquals(0, retrievedGuests.size());

    }

}
