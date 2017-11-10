package com.example.cmput301f17t30.habitrabbit;

import java.util.ArrayList;

/**
 * Created by arankin on 10/18/17.
 */

public class Friend {
    private String id;
    private ArrayList<HabitEvent> recentEvents;

    /**
     * @param id The unique identifier for the friend
     */
    public Friend(String id) {
        this.id = id;
        this.recentEvents= new ArrayList<HabitEvent>();
    }

    /**
     * @return The unique identifier for the friend
     */
    public String getId() {

        return id;
    }

    /**
     * @param id The unique identifier for the friend
     */
    public void setId(String id) {

        this.id = id;
    }

    /**
     *
     * @return The list of events that this friend has recently completed
     */
    public ArrayList<HabitEvent> getRecentEvents(){
        return recentEvents;
    }



}
