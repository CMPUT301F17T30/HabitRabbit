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
import static com.example.cmput301f17t30.habitrabbit.MainActivity.friendsList;

/**
 * Controller for managing friends and latest friend events
 */

public class FriendController {

    private ArrayList<Friend> friends = new ArrayList<>();

    public FriendController(){
    }

    public ArrayList<Friend> getFriends(){
        return friendsList.getFriends();
    }

    public void setFriendsList(ArrayList<Friend> friends){
        friendsList.setList(friends);
    }
}
