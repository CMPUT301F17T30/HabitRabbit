package com.example.cmput301f17t30.habitrabbit;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by arankin on 10/18/17.
 */

public class HabitEvent {
    private String Comment;
    private Habit habitType;

    private Date date;
    private Bitmap image;

    private String Location;
    private double logitude;
    private double latitude;


    public HabitEvent(Habit habitType, String location, String comment, double logitude, double latitude, Bitmap bitmap) {
        Comment = comment;
        this.habitType = habitType;
        Location = location;
        date = new Date();

        this.logitude = logitude;
        this.latitude = latitude;
        this.image = bitmap;
    }

    public HabitEvent(Habit habitType) {
        this.habitType = habitType;
        date = new Date();
    }

   /* public HabitEvent(Habit habitType, String location) {
        this.habitType = habitType;
        Location = location;
        date = new Date();

    }*/

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
    public void setCoordinate(double logitude, double latitude) {
        this.logitude = logitude;
        this.latitude = latitude;
    }

    public double getLogitude() {
        return logitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLocation(String location) {

        Location = location;
    }


    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
