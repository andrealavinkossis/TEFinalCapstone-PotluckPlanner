package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.techelevator.model.Contact;
import com.techelevator.model.UserNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.techelevator.model.User;

@Component
public class JdbcContactDao implements ContactDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcContactDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override

        public int create (Contact contact){
        String sql = "INSERT into contact (contact_name, contact_email, contact_message) values (?,?,?)";
        return jdbcTemplate.update(sql, contact.getContactName(), contact.getContactEmail(), contact.getContactMessage());



    }

}
