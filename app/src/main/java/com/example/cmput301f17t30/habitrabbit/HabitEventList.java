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

import java.util.ArrayList;

/**
 * A class used for a single instance of a list of habit events.
 * Created by Irissama on 2017-11-08.
 */

public class HabitEventList {
    private ArrayList<HabitEvent> eventList;

    /**
     * Constructor for habitEventList
     */
    public HabitEventList(){
        eventList = new ArrayList<>();
    }

    /**
     *
     * @param event a habit event that is to be added
     */
    public void addEvent(HabitEvent event){
        eventList.add(0, event);
    }

    /**
     *
     * @param index the index of the event that is to be edited
     */
    public void editEvent(int index, HabitEvent event){
        eventList.set(index, event);
    }

    /**
     *
     * @param index the index of the event that is to be deleted
     */
    public void deleteEvent(int index){
        eventList.remove(index);
    }

    /**
     *
     * @param index the index of the event
     *
     */
    public HabitEvent getEvent(int index){
        return eventList.get(index);
    }

    /**
     *
     * @return the list of events
     */
    public ArrayList<HabitEvent> getList(){
        return eventList;
    }


    public int getSize(){
        return eventList.size();
    }

    /**
     *
     * @param eventList the list of events
     */
    public void setEventList(ArrayList<HabitEvent> eventList) {
        this.eventList.addAll(eventList);
    }
}


