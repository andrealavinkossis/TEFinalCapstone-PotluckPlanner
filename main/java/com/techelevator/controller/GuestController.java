package com.techelevator.controller;

import com.techelevator.dao.GuestDao;
import com.techelevator.model.Guest;
import com.techelevator.model.PotluckNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class GuestController {

    private GuestDao guestDao;

    public GuestController(GuestDao guestDao) { this.guestDao = guestDao; }

    //addGuest
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value="/guest", method = RequestMethod.POST)
    public Guest addGuest(@Valid @RequestBody Guest guest) {

        try {
            return guestDao.addGuest(guest);
        } catch (PotluckNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Potluck Not Found!");
        }
    }

    //list all guests by potluck
    @RequestMapping(value = "/guest/{potluckId}", method = RequestMethod.GET)
    public List<Guest> getGuestsByPotluck(@Valid @PathVariable int potluckId) {
        return guestDao.getGuestsByPotluck(potluckId);
    }

    //remove guest --> SHOULD THIS BE A BOOLEAN OR A VOID?
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @DeleteMapping(value="/guest/{guestId}")
    public void removeGuest(@Valid @PathVariable int guestId) { }

}
