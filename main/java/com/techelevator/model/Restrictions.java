package com.techelevator.model;

public class Restrictions {

    public int restriction_id;
    public int user_id;
    public int dish_id;
    public String guest_email;
    public String restriction_type;





    public Restrictions (int restriction_id, int user_id, int dish_id, String guest_email, String restriction_type ) {
        this.restriction_id = restriction_id;
        this.user_id = user_id;
        this.dish_id = dish_id;
        this.guest_email = guest_email;
        this.restriction_type = restriction_type;

    }

    public Restrictions() {

    }


    public int getRestriction_id() {return restriction_id ;}
    public int getUser_id() {return user_id;}
    public int getDish_id() {return dish_id;}
    public String getGuest_email() {return guest_email;}
    public String getRestriction_type() {return restriction_type;}

    public String setRestriction_id(String restriction_id) {return restriction_id;}
    public int setUser_id(int user_id) {return user_id;}
    public int setDish_id(int dish_id) {return dish_id;}
    public String setGuest_email (String guest_email) {return  guest_email;}
    public String setRestriction_type (String  restriction_type) {return  restriction_type;}
}