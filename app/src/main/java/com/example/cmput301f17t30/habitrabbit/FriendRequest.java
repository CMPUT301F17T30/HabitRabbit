package com.example.cmput301f17t30.habitrabbit;

import io.searchbox.annotations.JestId;

import static com.example.cmput301f17t30.habitrabbit.MainActivity.userController;

/**
 * Model class representing a friend request.
 */

public class FriendRequest {
    private User sender;
    private User reciever;
    private Boolean accepted;

    @JestId
    private String id;

    public FriendRequest(User sender, User reciever){
        this.sender = sender;
        this.reciever = reciever;
        this.accepted = null;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReciever() {
        return reciever;
    }

    public void setReciever(User reciever) {
        this.reciever = reciever;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        if (accepted == Boolean.TRUE) {
            this.accepted = Boolean.TRUE;
        } else if (accepted == Boolean.FALSE) {
            this.accepted = Boolean.FALSE;
            //delete request
        }
    }

    public void checkRequestStatus(){
        if (getAccepted() == Boolean.TRUE){
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
