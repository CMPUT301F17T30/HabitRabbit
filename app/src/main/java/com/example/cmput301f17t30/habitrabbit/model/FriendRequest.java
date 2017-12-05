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

import com.example.cmput301f17t30.habitrabbit.Controllers.ElasticSearchController;

import io.searchbox.annotations.JestId;

import static com.example.cmput301f17t30.habitrabbit.Activities.MainActivity.userController;

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
     * @param sender the user who sent the request
     * @param reciever the user who the request was directed to
     */
    public FriendRequest(String sender, String reciever){
        this.sender = sender;
        this.reciever = reciever;
    }


    /**
     * gets the information on the user who sent the request
     * @return the user who sent the friend request
     */
    public User getSenderProfile(){
        User currentUser = userController.getUser();
        ElasticSearchController.GetSenderTask getSenderTask = new ElasticSearchController.GetSenderTask();
        getSenderTask.execute(sender);
        this.senderProfile = userController.getUser();
        userController.setUser(currentUser);
        return senderProfile;
    }

    /**
     * @param id unique identifier for elastic search
     */
    public void setId(String id){
        this.id = id;
    }

    /**
     * @return unique identifier for elastic search
     */
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


    /*
     *  do accept and decline action in activity, call elastic search
     */

}
