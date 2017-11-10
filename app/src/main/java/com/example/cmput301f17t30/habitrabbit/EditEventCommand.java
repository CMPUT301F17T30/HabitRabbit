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

public class EditEventCommand extends Command {
    @Override
    public void execute() {
        //POST Method
        //Index: Our Index
        //Type: Habit
        //ID: ID of Habit in elasticsearch
        /*
        * {
        *   "user-id" : "This is the user id"
        *   "title" : "This is a Title"
        *   "reason" : "This is a reason"
        *   "days" : "These are the days"
        *   "failed" : "This is how many times I failed" -- Do we add this?
        *   "completed" : "This is how many times I completed" -- Do we add this?
        * }
        * */
    }

    @Override
    public void unexecute() {

    }

    @Override
    public boolean isReversible() {
        return false;
    }
}
