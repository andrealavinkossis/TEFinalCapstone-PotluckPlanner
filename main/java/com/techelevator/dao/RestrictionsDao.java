
package com.techelevator.dao;

import com.techelevator.model.Restrictions;

public interface RestrictionsDao {


    void deleteRestrictions(int restriction_id);

    int updateRestrictions(Restrictions restrictions);

    int addRestrictions(int restriction_id);


    public int createRestrictions(Restrictions restrictions);


    //public String addGuestEmail();

    Restrictions getRestrictions(int restriction_id);
}
