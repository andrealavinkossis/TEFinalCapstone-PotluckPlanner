package com.techelevator.controller;

import com.techelevator.dao.PotluckDao;
import com.techelevator.model.Potluck;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class PotluckController {

private PotluckDao potluckDao;

public PotluckController(PotluckDao potluckDao) {
    this.potluckDao = potluckDao;
}

    // Create potluck
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/potluck", method = RequestMethod.POST)
    public Potluck addPotluck(@Valid @RequestBody Potluck newPotluck) {
        return potluckDao.createPotluck(newPotluck);
    }

    // Get all potlucks by user ID
    @RequestMapping(value = "/potluck/user/{userId}", method = RequestMethod.GET)
    public List<Potluck> getAllPotlucksByUserId(@Valid @PathVariable int userId) {
    return potluckDao.getAllPotlucksByUserId(userId);
}

    // Get all potlucks by host ID
 //   @RequestMapping(value = "/potluck/{hostId}", method = RequestMethod.GET)
//    public List<Potluck> getAllPotlucksByHostId(@Valid @PathVariable int hostId) {
//    return potluckDao.getAllPotlucksByHostId(hostId);
//    }

    // Get all potluck details by potluck ID - this will happen when you click on a specific potluck from your list
    @RequestMapping(value = "/potluck/{potluckId}", method = RequestMethod.GET)
    public Potluck getPotluckDetailsByPotluckId(@Valid @PathVariable int potluckId) {
    return potluckDao.getPotluckDetailsByPotluckId(potluckId);
    }

    // Update potluck details by potluck ID
    @RequestMapping(value = "/potluck/{potluckId}", method = RequestMethod.PUT)
    public Potluck updatePotluck(@Valid @RequestBody Potluck potluck, @PathVariable int potluckId) {
    return potluckDao.updatePotluck(potluck, potluckId);
    }

    // Delete potluck
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/potluck/{potluckId}", method = RequestMethod.DELETE)
    public void deletePotluck(@Valid @PathVariable int potluckId) {
    }


}
