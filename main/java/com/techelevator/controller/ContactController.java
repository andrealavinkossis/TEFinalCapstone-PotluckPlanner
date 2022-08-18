package com.techelevator.controller;

import javax.validation.Valid;

import com.techelevator.dao.ContactDao;
import com.techelevator.model.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techelevator.dao.UserDao;
import com.techelevator.security.jwt.JWTFilter;
import com.techelevator.security.jwt.TokenProvider;

@RestController
@CrossOrigin
public class ContactController {

private ContactDao contactDao;

    public ContactController(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public void setNewMessage(@Valid @RequestBody Contact newMessage) {
        try {
            contactDao.create(newMessage);
//
        } catch (IllegalArgumentException e) {

        }
    }

}