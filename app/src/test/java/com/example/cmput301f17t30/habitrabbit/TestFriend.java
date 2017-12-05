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

import static org.junit.Assert.*;

/**
 * Test class for friend model class
 * Created by arankin on 10/18/17.
 */

public class TestFriend  {

    public TestFriend() {
        super();
    }

    @Test
    public void testGetUser(){
        User user = new User("Bartholemew");
        Friend friend = new Friend(user);
        assertEquals(user,friend.getUser());
    }

    @Test
    public void testSetUser(){
        User user = new User("Bartholemew");
        Friend friend = new Friend(user);
        User newUser = new User("Hubert");
        friend.setUser(newUser);
        assertEquals(newUser,friend.getUser());
    }

    @Test
    public void testGetRecentEvents(){
        ArrayList<HabitEvent> events = new ArrayList<>();
        User user = new User("Bartholemew");
        Friend friend = new Friend(user);
        friend.setRecentEvents(events);
        assertEquals(events,friend.getRecentEvents());
    }

    @Test
    public void testSetRecentEvents(){
        ArrayList<HabitEvent> events = new ArrayList<>();
        User user = new User("Bartholemew");
        Friend friend = new Friend(user);
        friend.setRecentEvents(events);
        assertEquals(events,friend.getRecentEvents());
    }

}
