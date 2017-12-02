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

import io.searchbox.annotations.JestId;

/**
 * Class representing a user of the app.
 */

public class User {
    private  String userId;
    private ArrayList<String> friends;
    private Date joinDate;
    private Bitmap profilePic;
    private ArrayList<Integer> achievementProgress;

    @JestId
    private String id;

    /**
     * @param userId A unique identifier for the user
     */
    public  User(String userId) {
        this.userId = userId;
        friends = new ArrayList<>();
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

    /**
     * @return the list of friends that the user is following
     */
    public ArrayList<String> getFriendsList() {
        return friends;
    }

    /**
     * @param friendsList the list of friends that the user is following
     */
    public void setFriendsList(ArrayList<String> friendsList) {
        this.friends = friendsList;
    }

    /**
     * @param friend a new friend that the user is adding to his follow list
     */
    public void addFriend(String friend){
        this.friends.add(friend);
    }

    /**
     * @return the date that the user first used the app
     */
    public Date getJoinDate() {
        return joinDate;
    }

    /**
     * @param joinDate the date that the user first used the app
     */
    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    /**
     * @return the profile picture added by the the user
     */
    public Bitmap getProfilePic() {
        return profilePic;
    }

    /**
     * @param profilePic a profile pic added by the user
     */
    public void setProfilePic(Bitmap profilePic) {
        this.profilePic = profilePic;
    }

    /**
     * @return the numerical progress for each achievement
     */
    public ArrayList<Integer> getAchievementProgress() {
        return achievementProgress;
    }

    /**
     * @param achievementProgress the numerical progress for each achievement
     */
    public void setAchievementProgress(ArrayList<Integer> achievementProgress) {
        this.achievementProgress = achievementProgress;
    }

    /**
     * @param index the index of the achievement to update
     * @param newProgress the new progress for the specified achievment
     */
    public void updateAchievementProgress(Integer index, Integer newProgress){
        this.achievementProgress.set(index,newProgress);
    }

    public String getJestId(){return id;}

    public void setJestId(String id){this.id = id;}
}
