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
 * Model class representing a list of friend requests sent to the current user by other users
 */

public class FriendRequestList {
    private ArrayList<FriendRequest> requests= new ArrayList<>();

    /**
     * constructor
     */
    public FriendRequestList(){

    }

    /**
     * @param friendRequest a friend request sent to the current user by another user
     */
    public void addFriendRequest(FriendRequest friendRequest){
        requests.add(friendRequest);
    }

    /**
     * @param index the index of the request to delete
     */
    public void deleteFriendRequest(int index){
        requests.remove(index);
    }

    /**
     * @return list of friend requests sent to the current user by other users
     */
    public ArrayList<FriendRequest> getFriendRequests(){
        return requests;
    }

    /**
     * @param index the index of the request to get
     * @return the friend request at the specified index
     */
    public FriendRequest getRequest(int index){
        return requests.get(index);
    }

    /**
     * @param requests list of friend requests sent to the current user by other users
     */
    public void setRequestsList(ArrayList<FriendRequest> requests){this.requests = requests;}

}
