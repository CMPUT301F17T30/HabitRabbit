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

package com.example.cmput301f17t30.habitrabbit.model;

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
