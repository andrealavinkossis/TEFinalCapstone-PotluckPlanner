
package com.techelevator.dao;

import com.techelevator.model.Restrictions;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcRestrictionsDao implements RestrictionsDao {
    private JdbcTemplate jdbcTemplate;

    private RestrictionsDao restrictionDao;
    private Restrictions restrictions;

    private void JdbcRestrictionsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcRestrictionsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //update restriction where user_id will update restriction


    @Override
    public void deleteRestrictions(int restriction_id) {
        String sql = "SELECT restriction_id, user_id, dish_id, guest_email, restriction_type FROM restriction WHERE " +
                "restriction_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, restriction_id);
        if (result.next()) {
            restrictions = mapRowToRestrictions(result);
        }
    }

//need to fix sql
    @Override
    public int updateRestrictions(Restrictions restrictions) {
        String sql = "UPDATE restriction SET restriction_id = ?, user_id = ?, dish_id = ?, guest_email = ?, restriction_type = ?, WHERE " +
                "restriction_id = ?;";
        try {
        jdbcTemplate.update(sql, restrictions.getRestriction_id(), restrictions.getUser_id(), restrictions.getDish_id(), restrictions.getGuest_email(), restrictions.getRestriction_type(), restrictions.getRestriction_id());
        } catch (Exception e)

    {
        e.printStackTrace();
    }
    return 1;
    }

    @Override
    public int addRestrictions(int restriction_id) {
        return 0;
    }

    @Override
    public int createRestrictions( Restrictions restriction) {
        String sql = "INSERT INTO restriction (restriction_id, user_id, dish_id, guest_email, restriction_type) values (?,?,?,?,?)";
   jdbcTemplate.update(sql, restriction.getRestriction_id(), restriction.getUser_id(), restriction.getDish_id(), restriction.getGuest_email(), restriction.getRestriction_type());
        return 1;
    }


        @Override
        public Restrictions getRestrictions (int restriction_id) {
            String sql = "SELECT  restriction_id, user_id, dish_id, guest_email, restriction_type FROM restrictions WHERE" +
                    "restriction_id = ?;";
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, restriction_id);
            if (result.next()) {
                restrictions = mapRowToRestrictions(result);
            }
            return restrictions;
        }

        public Restrictions mapRowToRestrictions (SqlRowSet result){
            Restrictions restrictions = new Restrictions();
            restrictions.setRestriction_id(result.getString("restriction_id"));
            restrictions.setUser_id(result.getInt("user_id"));
            restrictions.setDish_id(result.getInt("dish_id"));
            restrictions.setGuest_email(result.getString("guest_email"));
            restrictions.setRestriction_type(result.getString("restriction_type"));

            return restrictions;
        }


}





