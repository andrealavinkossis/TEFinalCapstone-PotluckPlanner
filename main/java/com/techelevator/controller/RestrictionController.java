package com.techelevator.controller;

import com.techelevator.dao.RestrictionsDao;
import com.techelevator.model.Restrictions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin

public class RestrictionController {
        private RestrictionsDao restrictionsDao;

public RestrictionController(RestrictionsDao restrictionsDao) {
        this.restrictionsDao = restrictionsDao;
}

        //delete restriction
        @ResponseStatus(HttpStatus.NO_CONTENT)
        @RequestMapping(value = "/restrictions/{restriction_id}", method = RequestMethod.DELETE)
        public void deleteRestrictions(@Valid @PathVariable int restriction_id) {

        }

        {

        }

        //get a restriction
@RequestMapping(path = "/restrictions/{restriction_id}", method = RequestMethod.GET)
public Restrictions getARestriction(@Valid @PathVariable int restriction_id)  {
        return restrictionsDao.getRestrictions(restriction_id);
        }

        //update restriction
@RequestMapping(path = "/restrictions/{restriction_id}", method = RequestMethod.PUT)
public Restrictions updateRestrictions(@Valid @RequestBody Restrictions restrictions, @PathVariable int restriction_id)  {
        return null;// restrictionsDao.updateRestrictions(restrictions);
        }


        //create a restriction
@ResponseStatus(HttpStatus.CREATED)
@RequestMapping( path = "/restrictions", method = RequestMethod.POST)
public void createRestrictions(@Valid @RequestBody Restrictions newRestrictions) {
         restrictionsDao.createRestrictions(newRestrictions);
        }
}
