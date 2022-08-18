package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class Guest {

    @JsonProperty("guest_id")
    @Min(value=0)
    private int guestId;

    @JsonProperty("potluck_id")
    @Min(value=1)
    private int potluckId;

    @JsonProperty("username")
    @NotBlank
    private String userName;


    public Guest() {
    }

    public Guest(int guestId, int potluckId, String userName) {
        this.guestId = guestId;
        this.potluckId = potluckId;
        this.userName = userName;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public int getPotluckId() {
        return potluckId;
    }

    public void setPotluckId(int potluckId) {
        this.potluckId = potluckId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}







