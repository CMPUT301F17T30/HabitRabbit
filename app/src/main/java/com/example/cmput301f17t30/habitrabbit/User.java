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
 * Class representing a user of the app.
 */

public class User {
    private  String userId;
    private ArrayList<User> friendsList;
    private Date joinDate;
    private Bitmap profilePic;
    private ArrayList<Integer> achievementProgress;

    /**
     * @param userId A unique identifier for the user
     */
    public  User(String userId) {
        this.userId = userId;
    }

    /**
     * @return The unique identifier for the user
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId A unique identifier for the user
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<User> getFriendsList() {
        return friendsList;
    }

    public void setFriendsList(ArrayList<User> friendsList) {
        this.friendsList = friendsList;
    }

    public void addFriend(User friend){
        this.friendsList.add(friend);
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Bitmap getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Bitmap profilePic) {
        this.profilePic = profilePic;
    }

    public ArrayList<Integer> getAchievementProgress() {
        return achievementProgress;
    }

    public void setAchievementProgress(ArrayList<Integer> achievementProgress) {
        this.achievementProgress = achievementProgress;
    }
}
