

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

/**
 * Created by Jacqueline on 2017-11-11.
 */

public class HabitList {
    private ArrayList<Habit> habitList;

    public HabitList(){
        habitList = new ArrayList<Habit>();
    }

    public void addHabit(Habit habit){
        habitList.add(habit);
    }

    public void editEvent(int position, Habit habit){
        habitList.set(position, habit);
    }

    public void deleteHabit(int position){
        habitList.remove(position);
    }

    public Habit getHabit(int position){
        return habitList.get(position);
    }

    public int getSize(){
        return habitList.size();
    }

    public ArrayList<Habit> getList(){
        return habitList;
    }

}
