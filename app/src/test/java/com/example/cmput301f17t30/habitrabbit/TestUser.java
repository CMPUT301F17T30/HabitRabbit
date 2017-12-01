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


import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Test class for User model class
 * Created by arankin on 20/10/17.
 */

public class TestUser  {

    public TestUser(){
        super ();
    }
    @Test
    public void testGetId(){
        User user = new User("bob");
        assertEquals("bob",user.getUserId());
    }
    @Test
    public void testSetId(){
        User user = new User("bob");
        user.setUserId("timmy");
        assertEquals("timmy",user.getUserId());
    }
    @Test
    public void testGetJoinDate(){
        User user = new User("bob");
        Date today = new Date();
        user.setJoinDate(today);
        assertEquals(today,user.getJoinDate());
    }
    @Test
    public void testSetJoinDate(){
        User user = new User("bob");
        Date today = new Date();
        user.setJoinDate(today);
        assertEquals(today,user.getJoinDate());
    }
    @Test
    public void testGetFriendsList(){
        User user = new User("bob");
        ArrayList<User> friends = new ArrayList<>();
        User user1 = new User("Timmy");
        User user2 = new User("Biff");
        User user3 = new User("ZugZug");
        friends.add(user1);
        friends.add(user2);
        friends.add(user3);
        user.setFriendsList(friends);
        assertEquals(friends,user.getFriendsList());
    }
    @Test
    public void testAddFriend(){
        User user = new User("bob");
        ArrayList<User> friends = new ArrayList<>();
        User user1 = new User("Timmy");
        User user2 = new User("Biff");
        User user3 = new User("ZugZug");
        friends.add(user1);
        friends.add(user2);
        friends.add(user3);
        user.setFriendsList(friends);
        User newFriend = new User("Nebuchadnezzar");
        user.addFriend(newFriend);
        assertEquals(newFriend,user.getFriendsList().get(3));

    }
    @Test
    public void testGetAchievementProgress(){
        User user = new User("Nabonidus");
        ArrayList<Integer> progress = new ArrayList<>();
        progress.add(1);
        progress.add(3);
        user.setAchievementProgress(progress);
        assertEquals(progress,user.getAchievementProgress());

    }
    @Test
    public void testUpdateAchievementProgress(){
        User user = new User("Hammurabi");
        ArrayList<Integer> progress = new ArrayList<>();
        progress.add(1);
        progress.add(3);
        progress.add(7);
        user.setAchievementProgress(progress);
        user.updateAchievementProgress(1,5);
        assertEquals((Integer)5,user.getAchievementProgress().get(1));

    }


}
