package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Date;

public class Potluck {
    @JsonProperty("potluck_id")
    private int potluckId;
    @JsonProperty("potluck_name")
    private String potluckName;
    @JsonProperty("date_time")
    private LocalDateTime dateTime;
    @JsonProperty("description")
    private String description;
    @JsonProperty("user_id")
    private int userId;



    public Potluck() {
    }

    public int getPotluckId() {
        return potluckId;
    }

    public void setPotluckId(int potluckId) {
        this.potluckId = potluckId;
    }

    public String getPotluckName() {
        return potluckName;
    }

    public void setPotluckName(String potluckName) {
        this.potluckName = potluckName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
