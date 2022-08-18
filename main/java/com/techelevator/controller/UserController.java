package com.techelevator.controller;

import com.techelevator.dao.PotluckDao;
import com.techelevator.dao.RestrictionsDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Potluck;
import com.techelevator.model.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@CrossOrigin
public class UserController {

    private UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }


    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    public int userIdByUsername(@Valid @PathVariable String username) {
        try {
        return userDao.findIdByUsername(username);
        } catch (Exception e) {
            System.out.println(e);
        }
            return 0;
    }
    @RequestMapping(value = "/user/id/{id}", method = RequestMethod.GET)
    public User userById(@Valid @PathVariable int id) {
        try {
            return userDao.getUserById(id);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}




