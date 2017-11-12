/*
 *     <HabitRabbit- A habit tracking app.>
 *     Copyright (C) <2017>
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.example.cmput301f17t30.habitrabbit;

import android.graphics.Bitmap;

import java.util.Date;

import io.searchbox.annotations.JestId;

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
    
    // use for elasticsearch later.
    @JestId
    private String id;


    /**
     * @param habitType The type of habit that this event is an instance of
     * @param location Where this event was completed
     * @param comment A text comment of the event
     * @param logitude The longitude that the habit was completed at
     * @param latitude The longitude that the habit was completed at
     * @param bitmap A small image attached to a habit event
     */
    public HabitEvent(Habit habitType, String location, String comment, double logitude, double latitude, Bitmap bitmap) {
        Comment = comment;
        this.habitType = habitType;
        Location = location;
        date = new Date();

        this.logitude = logitude;
        this.latitude = latitude;
        this.image = bitmap;
    }

    /**
     * @param habitType The type of habit that this event is an instance of
     */
    public HabitEvent(Habit habitType) {
        this.habitType = habitType;
        date = new Date();
    }

   /* public HabitEvent(Habit habitType, String location) {
        this.habitType = habitType;
        Location = location;
        date = new Date();

    }*/
    public void setId(String id) {
        this.id = id;
    }

    public String getId(){
        return id;
    }


    /**
     * @return the date that this habitevent was completed
     */
    public Date getDate(){
        return date;
    }

    /**
     * @param date the date that this habitevent was completed
     */
    public void setDate(Date date){
        this.date = date;
    }

    /**
     * @return The text comment for this habit event
     */
    public String getComment() {
        return Comment;
    }

    /**
     * @param comment The text comment for this habit event
     */
    public void setComment(String comment) {
        Comment = comment;
    }

    /**
     * @return The type of habit that this event is an instance of
     */
    public Habit getHabitType() {
        return habitType;
    }

    /**
     * @param habitType The type of habit that this event is an instance of
     */
    public void setHabitType(Habit habitType) {
        this.habitType = habitType;
    }

    /**
     * @return The location at which this event was completed
     */

    public void setLocation(String location) {

        this.Location = location;
    }

    public String getLocation() {
        return Location;
    }

    /**
     * @param logitude Longitude where the even was completed
     * @param latitude Latitude where the even was completed
     */
    public void setCoordinate(double logitude, double latitude) {
        this.logitude = logitude;
        this.latitude = latitude;
    }

    /**
     *
     * @return Longitude where the even was completed
     */
    public double getLogitude() {
        return logitude;
    }

    /**
     * @return Latitude where the even was completed
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param location The location at which this event was completed
     */


    /**
     *
     * @return the image associated with this event
     */
    public Bitmap getImage() {
        return image;
    }

    /**
     * @param image a small image associated with this event
     */
    public void setImage(Bitmap image) {
        this.image = image;
    }
}
