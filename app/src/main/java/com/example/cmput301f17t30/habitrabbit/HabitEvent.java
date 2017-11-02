package com.example.cmput301f17t30.habitrabbit;

import android.media.Image;

import java.util.Date;

/**
 * Created by arankin on 10/18/17.
 */

public class HabitEvent {
    public String Comment;
    public Habit habitType;
    public Double Latitude;
    public Double Longitude;
    public Date date;
    public Image image;

    public HabitEvent(Habit habitType,  String comment) {
        Comment = comment;
        this.habitType = habitType;
        //Latitude = get current latitude
        //lonmgitude - get current long
        date = new Date();
    }

    public HabitEvent(Habit habitType) {
        this.habitType = habitType;
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

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
