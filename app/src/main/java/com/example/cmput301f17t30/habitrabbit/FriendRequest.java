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

package com.example.cmput301f17t30.habitrabbit;

import static com.example.cmput301f17t30.habitrabbit.MainActivity.userController;

/**
 * Model class representing a friend request.
 */

public class FriendRequest {
    private User sender;
    private User reciever;
    private Boolean accepted;

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
            userController.addFriend(reciever);
        }
        else if (getAccepted() == Boolean.FALSE){
            // user got rejected
        }
        else {
            // friend has not responded to request yet
        }
    }
}
