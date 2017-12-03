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

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static com.example.cmput301f17t30.habitrabbit.MainActivity.commandQueue;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.eventList;

/**
 * Controller for Haddingm, editing, and deleting habit events
 * Created by Irissama on 2017-11-08.
 */

public class HabitEventController {

    private HabitEvent habitEvent;
    private int position;
    private static int flag;

    public HabitEventController(){
        flag = 0;
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

    public void setUserId(String userId){habitEvent.setUserId(userId);}

    public String getUserId(){return habitEvent.getUserId();}

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

    public void setDate(Date date){
        habitEvent.setDate(date);
    }

    public void saveAddEvent(){
        eventList.addEvent(habitEvent);
        AddEventCommand addEventCommand = new AddEventCommand(habitEvent);
        commandQueue.addTail(addEventCommand);
        habitEvent.getHabitType().incrementTimesCompleted();
        habitEvent.getHabitType().setLastCompleted(new Date());
    }

    public void deleteEvent(int index){
        eventList.deleteEvent(index);
        DeleteEventCommand deleteEventCommand = new DeleteEventCommand(habitEvent);
        commandQueue.addTail(deleteEventCommand);
        habitEvent.getHabitType().decrementTimesCompleted();
        flag = 1;
    }

    public void resetDelete(){
        flag = 0;
    }

    public int isDelete(){

        return flag;
    }

    public void saveEditEvent(int index){
        eventList.editEvent(index, habitEvent);
        EditEventCommand editEventCommand = new EditEventCommand(habitEvent);
        commandQueue.addTail(editEventCommand);

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

    public void deleteAllHabitEvents(Habit type) {
        ArrayList<HabitEvent> list = eventList.getList();
        try {
            habitEvent = eventList.getEvent(0);
            for (Iterator<HabitEvent> iterator = list.iterator(); iterator.hasNext(); ) {
                if (iterator.next().getHabitType() == type)
                    iterator.remove();
                DeleteEventCommand deleteEventCommand = new DeleteEventCommand(habitEvent);
                commandQueue.addTail(deleteEventCommand);
                if (iterator.hasNext())
                    habitEvent = iterator.next();
            }
        } catch(IndexOutOfBoundsException e) {
            Log.i("Delete Habit", "No HabitEvents in Habit");
        }
        eventList.setEventList(list);
    }

     public HabitEvent returnEvent(){
     return habitEvent;
    }

    public void setEvent(HabitEvent event){
        this.habitEvent = event;
    }

}
