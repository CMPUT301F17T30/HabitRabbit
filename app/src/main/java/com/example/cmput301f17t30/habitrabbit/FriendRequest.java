package com.example.cmput301f17t30.habitrabbit;

import io.searchbox.annotations.JestId;

import static com.example.cmput301f17t30.habitrabbit.MainActivity.userController;

/**
 * Model class representing a friend request. Can be accepted by the reciever.
 */

public class FriendRequest {
    private String sender;
    private String reciever;
    private User senderProfile;

    @JestId
    private String id;



    /**
     *
     * @param sender the user who sent the request
     * @param reciever the user who the request was directed to
     */
    public FriendRequest(String sender, String reciever){
        this.sender = sender;
        this.reciever = reciever;
    }


    public User getSenderProfile(){
        User currentUser = userController.getUser();
        ElasticSearchController.GetSenderTask getSenderTask = new ElasticSearchController.GetSenderTask();
        getSenderTask.execute(sender);
        this.senderProfile = userController.getUser();
        userController.setUser(currentUser);
        return senderProfile;
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
    public String getSender() {
        return sender;
    }

    /**
     *
     * @param sender the user who sent the request
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * @return the user who the request was directed to
     */
    public String getReciever() {
        return reciever;
    }

    /**
     * @param reciever the user who the request was directed to
     */
    public void setReciever(String reciever) {
        this.reciever = reciever;
    }


    /**
     *  do accept and decline action in activity, call elastic search
     */

}
