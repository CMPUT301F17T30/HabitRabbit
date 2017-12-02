package com.example.cmput301f17t30.habitrabbit;

import java.util.ArrayList;

/**
 * Class used for storing a list of user's friends
 */

public class FriendList {
    private ArrayList<User> friends;
    private ArrayList<HabitEvent> latestEvents;

    public FriendList(){
        this.friends = new ArrayList<>();
        this.latestEvents = new ArrayList<>();
    }

    public void setList(ArrayList<User> friends){
        this.friends = friends;
    }

    public void getLatestEvents(){
        for (User f : friends){
            //ElasticSearchController.GetHabitsTask
            //ElasticSearchController.AddEventTask AddLatestEvent = new ElasticSearchController.AddEventTask();

        }
    }
}
