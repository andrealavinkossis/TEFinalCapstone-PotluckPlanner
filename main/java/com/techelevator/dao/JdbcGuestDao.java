package com.techelevator.dao;

import com.techelevator.model.Course;
import com.techelevator.model.Guest;
//import com.techelevator.model.Potluck; IMPORT ME?
import com.techelevator.model.PotluckNotFoundException;
import com.techelevator.model.UserNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
    public class JdbcGuestDao implements GuestDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcGuestDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Guest mapRowToGuest(SqlRowSet rowSet) {
        Guest guest = new Guest();
        guest.setGuestId(rowSet.getInt("guest_id"));
        guest.setPotluckId(rowSet.getInt("potluck_id"));
        guest.setUserName(rowSet.getString("username"));
        return guest;
    }

    @Override
    public Guest addGuest(Guest guest) throws PotluckNotFoundException {
        try {
            String sql = "INSERT INTO guest (guest_id, potluck_id, username) VALUES (default, ?, ?) RETURNING guest_id;";
            Integer newGuestId = jdbcTemplate.queryForObject(sql, Integer.class, guest.getPotluckId(), guest.getUserName());
            guest.setGuestId(newGuestId);
            return guest;
        } catch (Exception e) {
            throw new PotluckNotFoundException();
        }
    }

    @Override
    public List<Guest> getGuestsByPotluck(int potluckId) { // don't know that we need to throw this exception here
        List<Guest> allGuests = new ArrayList<>();
        String sql = "SELECT guest_id, potluck_id, username FROM guest WHERE potluck_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, potluckId);
        while (result.next()) {
            allGuests.add(mapRowToGuest(result));
        }
        return allGuests;
    }

    @Override
    public void removeGuest(int guestId) { // does this want to be false

        String deleteFromDishSql = "DELETE FROM dish WHERE guest_id = ?;";
        jdbcTemplate.update(deleteFromDishSql, guestId);

        String deleteFromGuestListSql = "DELETE FROM guest WHERE guest_id = ?;";
        jdbcTemplate.update(deleteFromGuestListSql, guestId);

    }
}
