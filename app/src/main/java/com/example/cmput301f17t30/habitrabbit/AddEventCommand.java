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

/**
 * Created by WilliamWong on 2017-11-08.
 */

public class AddEventCommand extends Command {
    @Override
    public void execute() {
        //POST Method
        //Index: Our Index
        //Type: Event
        /*
        * {
        *   "user-id" : "This is the User ID"
        *   "comment" : "This is the comment"
        *   "habittype" : "This is the Habit I'm part of"
        *   "date" : "This is the date"
        *   "image" : "This is the image"
        *   "location" : "This is the location"
        *   "longitude" : "This is the longitude"
        *   "latitude" : "This is the latitude"
        * }
        * */
        //Find a way to store Event ID from elasticsearch
    }

    @Override
    public void unexecute() {

    }

    @Override
    public boolean isReversible() {
        return false;
    }
}
