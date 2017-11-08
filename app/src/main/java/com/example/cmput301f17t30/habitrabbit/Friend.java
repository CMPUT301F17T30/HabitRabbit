package com.example.cmput301f17t30.habitrabbit;

import java.util.ArrayList;

/**
 * Created by arankin on 10/18/17.
 */

public class Friend {
    private String id;
    private ArrayList<HabitEvent> recentEvents;

    public Friend(String id) {
        this.id = id;
        this.recentEvents= new ArrayList<HabitEvent>();
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public ArrayList<HabitEvent> getRecentEvents(){
        return recentEvents;
    }



}
