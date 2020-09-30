package com.example.taskmanager.model;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

public class Task {
    private String mTitle;
    private String mDescription;
    private Date mDate;
    private State state;
    private UUID mId;

    public Task() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }



    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public Date getDate() {
        return mDate;
    }

    public State getState() {
        return state;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public void setState(State state) {
        this.state = state;
    }
}
