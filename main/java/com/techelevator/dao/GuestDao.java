package com.techelevator.dao;


import com.techelevator.model.*;

import java.util.List;

public interface GuestDao {

    Guest addGuest(Guest guest) throws PotluckNotFoundException;
    //HOST can use in Potluck to add guests
    //should return the new guestEmail
    //throwing an exception in case they want to add someone who is not a user
    //HOWEVER this could mess things up if they are supposed to be able to add someone who hasn't registered as a user yet.

    List<Guest> getGuestsByPotluck(int potluckId);
    //this provides a list of guests when someone opens up a potluck

    void removeGuest(int guestId);
    //HOST can use in Potluck if a host wants to remove a guest.
    //should return true if number of rows deleted = 1

}
