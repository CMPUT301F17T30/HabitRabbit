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


import java.util.ArrayList;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Date;

/**
 * Controller to deal with adding new users,
 * and logging out and in
 */

public class UserController {

    private User user;

    public UserController(){
    }

    //TODO fix this method
    /**
     *
     * @param username
     */
    public void setUser(String username){
        this.user = new User(username);
    }

    /**
     * removes the current user, used when user logs out
     */
    public void clearUser(){
        this.user = null;
    }

    /**
     * @return the user's username if some user is logged in, otherwise null
     */
    public String getUsername(){
        try {
            return user.getUserId();
        }
        catch (NullPointerException exception) {
            return null;
        }
    }


    public ArrayList<String> getFriends(){
        return user.getFriendsList();
    }

    /**
     *
     * @param friends list of friends that the user is following
     */
    public void setFriends(ArrayList<String> friends){
        user.setFriendsList(friends);
    }

    /**
     * @param friend a user that current user wishes to follow
     */
    public void addFriend(String friend){
        user.addFriend(friend);
    }

    /**
     * @return the date when this user first opened app
     */
    public Date getJoinDate(){
        return user.getJoinDate();
    }

    /**
     *
     * @param date the date when this user first opened app
     */
    public void setJoinDate(Date date){
        user.setJoinDate(date);
    }

    /**
     * @param newPic current user's profile picture
     */
    public void setProfilePicture(Bitmap newPic){
        user.setProfilePic(newPic);
    }

    /**
     * @return current user's profile picture
     */
    public Bitmap getProfilePic(){
        return user.getProfilePic();
    }



}
