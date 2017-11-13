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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import static com.example.cmput301f17t30.habitrabbit.MainActivity.eventList;

/**
 * Controller for Haddingm, editing, and deleting habit events
 * Created by Irissama on 2017-11-08.
 */

public class HabitEventController {

    private HabitEvent habitEvent;
    private int position;

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

    public void setDate(){
        habitEvent.setDate(new Date());
    }

    public void saveAddEvent(){
        eventList.addEvent(habitEvent);
        habitEvent.getHabitType().incrementTimesCompleted();
        habitEvent.getHabitType().setLastCompleted(new Date());
    }

    public void deleteEvent(int index){
        eventList.deleteEvent(index);
        habitEvent.getHabitType().decrementTimesCompleted();
    }

    public void saveEditEvent(){
        eventList.editEvent(position, habitEvent);

    }

    public void sortByDate(){


        ArrayList<HabitEvent> list = eventList.getList();

        Collections.sort(list, new Comparator<HabitEvent>() {
            @Override
            public int compare(HabitEvent lhs, HabitEvent rhs) {
                return lhs.getDate().compareTo(rhs.getDate());
            }
        });

        eventList.setEventList(list);
    }


}
