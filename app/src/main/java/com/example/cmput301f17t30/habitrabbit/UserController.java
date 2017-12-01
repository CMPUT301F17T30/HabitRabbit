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

    public void setUser(String username){
        this.user = new User(username);
    }

    public void clearUser(){
        this.user = null;
    }

    public String getUsername(){
        try {
            return user.getUserId();
        }
        catch (NullPointerException exception) {
            return null;
        }
    }

    public void setFriends(ArrayList<User> friends){
        user.setFriendsList(friends);
    }

    public void addFriend(User friend){
        user.addFriend(friend);
    }

    public Date getJoinDate(){
        return user.getJoinDate();
    }

    public void setJoinDate(Date date){
        user.setJoinDate(date);
    }

    public void setProfilePicture(Bitmap newPic){
        user.setProfilePic(newPic);
    }

    public Bitmap getProfilePic(){
        return user.getProfilePic();
    }



}
