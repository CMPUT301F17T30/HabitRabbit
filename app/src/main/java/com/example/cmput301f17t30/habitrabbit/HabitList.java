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
 * A class representing a list of habit objects.
 */
public class HabitList {
    private ArrayList<Habit> habitList;

    /**
     * Constructor for habitList
     */
    public HabitList(){
        habitList = new ArrayList<>();
    }

    /**
     *
     * @param habit the habit that we wish to add
     */
    public void addHabit(Habit habit){
        habitList.add(habit);
    }

    /**
     *
     * @param position the index of the habit that we wish to edit
     * @param habit the habit that we wish to edit
     */
    public void editHabit(int position, Habit habit){
        habitList.set(position, habit);
    }

    /**
     *
     * @param position the index of the habit that we wish to delete
     */
    public void deleteHabit(int position){
        habitList.remove(position);
    }

    /**
     *
     * @param position the index of the habit what we wish to get
     * @return the habit at the specified position
     */
    public Habit getHabit(int position){
        return habitList.get(position);
    }

    /**
     *
     * @return the number of hbaits in the list
     */
    public int getSize(){
        return habitList.size();
    }

    /**
     *
     * @return the list of habits
     */
    public ArrayList<Habit> getList(){
        return habitList;
    }

}
