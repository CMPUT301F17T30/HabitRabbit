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

import static org.junit.Assert.*;

/**
 * Test class for the model class Friendrequest
 */

public class TestFriendRequest {

    public TestFriendRequest(){ super(); }

    @Test
    public void testGetSender(){
        User sender = new User();
        User reciever = new User();
        FriendRequest request = new FriendRequest(sender,reciever);
        assertEquals(sender,request.getSender());
    }
    @Test
    public void testSetSender(){
        User sender = new User();
        User reciever = new User();
        FriendRequest request = new FriendRequest(sender,reciever);
        User newSender = new User();
        request.setSender(newSender);
        assertEquals(newSender,request.getSender());
    }
    @Test
    public void testGetReciever(){
        User sender = new User();
        User reciever = new User();
        FriendRequest request = new FriendRequest(sender,reciever);
        assertEquals(reciever,request.getReciever());
    }

    @Test
    public void testSetReciever(){
        User sender = new User();
        User reciever = new User();
        FriendRequest request = new FriendRequest(sender,reciever);
        User newReciever = new User();
        request.setReciever(newReciever);
        assertEquals(newReciever,request.getReciever());
    }

    @Test
    public void testSetAccepted(){
        User sender = new User();
        User reciever = new User();
        FriendRequest request = new FriendRequest(sender,reciever);
        request.setAccepted(Boolean.TRUE);
        assertEquals(Boolean.TRUE,request.getAccepted());
        request.setAccepted(Boolean.FALSE);
        assertEquals(Boolean.FALSE,request.getAccepted());

    }
}
