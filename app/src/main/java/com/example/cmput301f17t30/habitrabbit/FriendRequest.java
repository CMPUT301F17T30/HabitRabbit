package com.example.cmput301f17t30.habitrabbit;

import io.searchbox.annotations.JestId;

import static com.example.cmput301f17t30.habitrabbit.MainActivity.userController;

/**
 * Model class representing a friend request. Can be accepted by the reciever.
 */

public class FriendRequest {
    private User sender;
    private User reciever;

    @JestId
    private String id;


    /**
     *
     * @param sender the user who sent the request
     * @param reciever the user who the request was directed to
     */
    public FriendRequest(User sender, User reciever){
        this.sender = sender;
        this.reciever = reciever;
    }


    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }
    /**
     * @return the user who sent the request
     */
    public User getSender() {
        return sender;
    }

    /**
     *
     * @param sender the user who sent the request
     */
    public void setSender(User sender) {
        this.sender = sender;
    }

    /**
     * @return the user who the request was directed to
     */
    public User getReciever() {
        return reciever;
    }

    /**
     * @param reciever the user who the request was directed to
     */
    public void setReciever(User reciever) {
        this.reciever = reciever;
    }


    /**
     *  do accept and decline action in activity, call elastic search
     */

}
