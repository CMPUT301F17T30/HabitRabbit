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

import android.content.Context;
import android.media.Image;

import java.util.ArrayList;
import java.util.Date;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.habitList;

/**
 * Created by Jacqueline on 2017-11-11.
 */

public class HabitController {
    private Habit habit;
    private int position;

    public HabitController(){

    }

    public void addHabit(String title, ArrayList<Boolean> days, Date startDate){
        habit = new Habit(title, days, startDate);
    }

    public void setTitle(String title){
        habit.setTitle(title);
    }

    public void setReason(String reason){
        habit.setReason(reason);
    }

    public void setDays(ArrayList<Boolean> days){
        habit.setDays(days);
    }

    public void setDate(Date date){
        habit.setStartDate(date);
    }

    public String getTitle(){
        return habit.getTitle();
    }

    public String getReason(){
        return habit.getReason();
    }

    public int getCompleted(){
        return habit.getTimesCompleted();
    }
    public int getFailed(){
        return habit.getTimesFailed();
    }

    public ArrayList<Boolean> getDays(){
        return habit.getDays();
    }

    public Date getStartDate(){
        return habit.getStartDate();
    }

    public double getPercentageCompletion(){
        habit.updateFailed();
        return habit.getPercentCompletion();
    }

    public void saveAddHabit(){
        habitList.addHabit(habit);
    }

    public void saveEditHabit(){
        habitList.editEvent(position, habit);
    }

    public void viewHabit(int position){
        habit = habitList.getHabit(position);
        this.position = position;
    }

}
