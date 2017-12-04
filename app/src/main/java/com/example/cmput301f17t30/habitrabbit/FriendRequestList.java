package com.example.cmput301f17t30.habitrabbit;

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
