package com.example.cmput301f17t30.habitrabbit;

import android.graphics.Bitmap;

import java.util.Date;

import static com.example.cmput301f17t30.habitrabbit.MainActivity.eventList;

/**
 * Created by Irissama on 2017-11-08.
 */

public class HabitEventController {

    private HabitEvent habitEvent;

    public HabitEventController(){
    }

    public void addEvent(Habit habit){
        habitEvent = new HabitEvent(habit);
    }

    public void editEvent(int index){
        habitEvent = eventList.getEvent(index);
    }


    public void setCoordinate(double logtitude, double latitude){
        habitEvent.setCoordinate(logtitude, latitude);
    }

    public void setLocationName(String locationName){
        habitEvent.setLocation(locationName);
    }

    public void setImage(Bitmap bitmap){
        habitEvent.setImage(bitmap);
    }

    public void setComment(String comment){
        habitEvent.setComment(comment);
    }

    public Bitmap getImage(int index){
        habitEvent = eventList.getEvent(index);
        return habitEvent.getImage();
    }

    public Date getDate(int index){
        habitEvent = eventList.getEvent(index);
        return habitEvent.getDate();
    }

    public String getLocation(int index){
        habitEvent = eventList.getEvent(index);
        return habitEvent.getLocation();
    }

    public Habit getType(int index){
        habitEvent = eventList.getEvent(index);
        return habitEvent.getHabitType();
    }

    public String getComment(int index){
        habitEvent = eventList.getEvent(index);
        return habitEvent.getComment();
    }

    public double getLogitude(int index){
        habitEvent = eventList.getEvent(index);
        return habitEvent.getLogitude();
    }

    public double getLatitude(int index){
        habitEvent = eventList.getEvent(index);
        return habitEvent.getLatitude();
    }

    public void saveAddEvent(){
        eventList.addEvent(habitEvent);
        habitEvent.getHabitType().incrementTimesCompleted();
    }

    public void deleteEvent(int index){
        eventList.deleteEvent(index);
        habitEvent.getHabitType().decrementTimesCompleted();
    }

    public void saveEditEvent(int index){
        eventList.editEvent(index, habitEvent);

    }

}
