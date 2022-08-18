package com.techelevator.dao;

import com.techelevator.model.Potluck;

import java.util.Date;
import java.util.List;

public interface PotluckDao {


    List<Potluck> getAllPotlucks();

    Potluck getPotluckDetailsByPotluckId(int potluckId);

    List<Potluck> getAllPotlucksByUserId(int userId);

    List<Potluck> getAllPotlucksByGuestId(int guestId);


    // Create a potluck - this method needs more work
    Potluck createPotluck(Potluck potluck);

    Potluck updatePotluck(Potluck potluck, int potluckId);

    Potluck updateDateTime(int potluckId);

    Potluck updatePotluckName(int potluckId);

    Potluck updatePotluckDescription(int potluckId);

    public void deletePotluck(int potluckId);





}
