package com.techelevator.dao;

import com.techelevator.model.Potluck;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPotluckDao implements PotluckDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcPotluckDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Potluck> getAllPotlucks() { // NOT CURRENTLY USING THIS METHOD
        List<Potluck> potlucks = new ArrayList<>();
        String sql = "SELECT potluck_id, potluck_name, date_time, description, user_id FROM potluck;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        while (result.next()) {
            potlucks.add(mapRowToPotluck(result));
        }
        return potlucks;
    }

    // Get all potluck details when you click on that specific potluck
    @Override
    public Potluck getPotluckDetailsByPotluckId(int potluckId) { // Validate permission access for guest vs host
        Potluck potluck = null;
        String sql = "SELECT potluck_id, potluck_name, date_time, description, user_id FROM potluck WHERE " +
                "potluck_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, potluckId);
        if(result.next()) {
            potluck = mapRowToPotluck(result);
        }
        return potluck;
    }

    // Get all potlucks by user ID
    @Override
    public List<Potluck> getAllPotlucksByUserId(int userId) {
        List<Potluck> potlucks = new ArrayList<>();
        String sql = "SELECT potluck_id, potluck_name, date_time, description, user_id FROM potluck WHERE " +
                "user_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
        while (result.next()) {
            potlucks.add(mapRowToPotluck(result));
        }
        return potlucks;
    }

    // DON'T USE THIS ONE
    // Get all potlucks by guest ID - get rid of this one
    @Override
    public List<Potluck> getAllPotlucksByGuestId(int guestId) { // Join Potluck table with Guest table
        List<Potluck> potlucks = new ArrayList<>();
        String sql = "SELECT potluck_id, potluck_name, date_time, description, host_id FROM potluck WHERE " +
                "guest_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, guestId);
        while (result.next()) {
            potlucks.add(mapRowToPotluck(result));
        }
        return potlucks;
    }


    // Create a potluck
    @Override
    public Potluck createPotluck(Potluck potluck) {
        String sql = "INSERT INTO potluck (potluck_id, potluck_name, date_time, description, user_id) " +
                "VALUES (default, ?, ?, ?, ?) " + // Use "?" instead of subquery for user_id (Pull the user.id from the $store)
                "RETURNING potluck_id;";
        //try {
            Integer newPotluckId = jdbcTemplate.queryForObject(sql, Integer.class, potluck.getPotluckName(), potluck.getDateTime(), potluck.getDescription(), potluck.getUserId() );
        potluck.setPotluckId(newPotluckId);
        String sql2 = "INSERT INTO course (course_id, potluck_id, amount_needed, course_name) Values (default, ?, 0, 'Main Course');" +
                "INSERT INTO course (course_id, potluck_id, amount_needed, course_name) Values (default, ?, 0, 'Appetizers');" +
                "INSERT INTO course (course_id, potluck_id, amount_needed, course_name) Values (default, ?, 0, 'Beverages');" +
                "INSERT INTO course (course_id, potluck_id, amount_needed, course_name) Values (default, ?, 0, 'Desserts');";
            jdbcTemplate.update(sql2, newPotluckId,newPotluckId,newPotluckId,newPotluckId);
       // } catch (Exception e) {
            //return null;
       // }

        return potluck;
    }


    // Update potluck details by potluck ID
    @Override
    public Potluck updatePotluck(Potluck potluck, int potluckId) { // Validate permission access for host vs guest
        String sql = "UPDATE potluck SET potluck_name = ?, date_time = ?, description = ? WHERE potluck_id = ?;";
      // try {
           jdbcTemplate.update(sql, potluck.getPotluckName(), potluck.getDateTime(), potluck.getDescription(), potluckId);
        //} catch (Exception e) {
          // e.printStackTrace();
      // }
       return potluck;
    }

    // We decided not to use this
    @Override
    public Potluck updateDateTime(int potluckId) {
        String sql = "UPDATE potluck SET date_time = ? WHERE potluck_id = ?;";
        return null;
    }

    // We decided not to use this
    @Override
    public Potluck updatePotluckName(int potluckId) {
        String sql = "UPDATE potluck SET potluck_name = ? WHERE potluck_id = ?;";
        return null;
    }

    // We decided not to use this
    @Override
    public Potluck updatePotluckDescription(int potluckId) {
        String sql = "UPDATE potluck SET description = ? WHERE potluck_id = ?;";
        return null;
    }

    // Delete a potluck - this method needs more work (WE AREN'T USING THIS METHOD)
    @Override
    public void deletePotluck(int potluckId) {
        String sql = "DELETE from potluck WHERE potluck_id = ?;";
        jdbcTemplate.update(sql, potluckId);
    }

    private Potluck mapRowToPotluck(SqlRowSet rowSet) {
        Potluck potluck = new Potluck();
        potluck.setPotluckId(rowSet.getInt("potluck_id"));
        potluck.setPotluckName(rowSet.getString("potluck_name"));
        potluck.setDateTime(rowSet.getTimestamp("date_time").toLocalDateTime());
        potluck.setDescription(rowSet.getString("description"));
        potluck.setUserId(rowSet.getInt("user_id"));
        return potluck;
    }
}
