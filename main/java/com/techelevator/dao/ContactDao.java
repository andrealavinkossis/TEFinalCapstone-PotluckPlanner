package com.techelevator.dao;

import com.techelevator.model.Contact;
import com.techelevator.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

public interface ContactDao {

    int create(Contact contact);

}

