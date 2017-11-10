package com.example.cmput301f17t30.habitrabbit;

import java.util.ArrayList;

/**
 * Created by Irissama on 2017-11-08.
 */

public class HabitEventList {
    private ArrayList<HabitEvent> eventList;

    public HabitEventList(){
        eventList = new ArrayList<HabitEvent>();
    }

    public void addEvent(HabitEvent event){
        eventList.add(event);
    }

    public void editEvent(int index, HabitEvent event){
        eventList.set(index, event);
    }

    public void deleteEvent(int index){
        eventList.remove(index);
    }

    public HabitEvent getEvent(int index){
        return eventList.get(index);
    }
}
