package com.example.cmput301f17t30.habitrabbit;

import android.media.Image;

import java.util.Date;

/**
 * Created by arankin on 10/18/17.
 */

public class HabitEvent {
    public String Comment;
    public Habit habitType;
    public String Location;
    public Date date;
    public Image image;

    public HabitEvent(Habit habitType, String location, String comment) {
        Comment = comment;
        this.habitType = habitType;
        Location = location;
        date = new Date();
    }

    public HabitEvent(Habit habitType) {
        this.habitType = habitType;
        date = new Date();
    }

    public HabitEvent(Habit habitType, String location) {
        this.habitType = habitType;
        Location = location;
        date = new Date();

    }

    public Date getDate(){
        return date;
    }
    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public Habit getHabitType() {

        return habitType;
    }

    public void setHabitType(Habit habitType) {

        this.habitType = habitType;
    }

    public String getLocation() {

        return Location;
    }

    public void setLocation(String location) {

        Location = location;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
