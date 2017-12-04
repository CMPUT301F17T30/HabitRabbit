package com.example.cmput301f17t30.habitrabbit;

import java.util.ArrayList;

/**
 * Created by Irissama on 2017-12-02.
 */

public class FriendRequestList {
    private ArrayList<FriendRequest> requests= new ArrayList<>();

    public FriendRequestList(){

    }

    public void addFriendRequest(FriendRequest friendRequest){
        requests.add(friendRequest);
    }

    public void deleteFriendRequest(int index){
        requests.remove(index);
    }

    public ArrayList<FriendRequest> getFriendRequests(){
        return requests;
    }

    public FriendRequest getRequest(int index){
        return requests.get(index);
    }

}
