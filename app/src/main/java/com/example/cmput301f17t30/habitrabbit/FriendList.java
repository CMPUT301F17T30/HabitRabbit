package com.example.cmput301f17t30.habitrabbit;

import java.util.ArrayList;

/**
 * Class used for storing a list of user's friends
 */

public class FriendList {
    private ArrayList<Friend> friends;
    private ArrayList<HabitEvent> latestEvents;

    public FriendList(){
        this.friends = new ArrayList<>();
        this.latestEvents = new ArrayList<>();
    }

    /**
     * @param friends current user's list of friends
     */
    public void setList(ArrayList<Friend> friends){
        this.friends = friends;
    }

    /**
     * @return the size of user's friend list
     */
    public Integer getSize(){return friends.size();}

    /**
     * @param friend the user to be added as a friend
     */
    public void addFriend(Friend friend){friends.add(friend);}

    /**
     * @return the current user's list of friends
     */
    public ArrayList<Friend> getFriends(){return friends;}

    public void getLatestEvents(){
        for (Friend f : friends){
            //ElasticSearchController.GetHabitsTask
            //ElasticSearchController.AddEventTask AddLatestEvent = new ElasticSearchController.AddEventTask();

        }
    }
}
