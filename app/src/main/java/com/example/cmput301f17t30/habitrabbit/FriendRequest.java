package com.example.cmput301f17t30.habitrabbit;

import io.searchbox.annotations.JestId;

import static com.example.cmput301f17t30.habitrabbit.MainActivity.userController;

/**
 * Model class representing a friend request. Can be accepted by the reciever.
 */

public class FriendRequest {
    private User sender;
    private User reciever;
    private Boolean accepted;

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
        this.accepted = null;
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
     * @return whether or not the request was accepted or rejected
     */
    public Boolean getAccepted() {
        return accepted;
    }

    /**
     * @param accepted whether or not the request was accepted or rejected
     */
    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    /**
     *  check if the reciever has rejected or accepted the request yet
     *  if he has accepted, then add the friend to user's friend list
     */
    public void checkRequestStatus(){
        if ((getAccepted() == Boolean.TRUE) && (userController.getUsername().equals(this.reciever.getUserId()))){
            //reciever has accepted, add the reciever to friends list
            userController.addFriend(reciever.getUserId());
        }
        else if (getAccepted() == Boolean.FALSE){
            // user got rejected
        }
        else {
            // friend has not responded to request yet
        }
    }
}
