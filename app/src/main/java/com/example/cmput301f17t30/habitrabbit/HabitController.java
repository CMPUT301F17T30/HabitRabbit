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
import java.util.Date;

import static com.example.cmput301f17t30.habitrabbit.MainActivity.commandQueue;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.eventController;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.habitList;

/**
 * Controller to deal with adding, editing and deleting habits.
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

    /**
     * @return the title of the current habit
     */
    public String getTitle(){
        return habit.getTitle();
    }

    /**
     * @param title the title of the current habit
     */
    public void setTitle(String title){
        habit.setTitle(title);
    }

    /**
     * @return the reason for completing this habit
     */
    public String getReason(){
        return habit.getReason();
    }

    /**
     * @param reason the reason for completing this habit
     */
    public void setReason(String reason){
        habit.setReason(reason);
    }

    /**
     * @return the days on which current habit is due
     */
    public ArrayList<Boolean> getDays(){
        return habit.getDays();
    }

    /**
     * @param days the days on which current habit is due
     */
    public void setDays(ArrayList<Boolean> days){
        habit.setDays(days);
    }

    /**
     * @param date start date of current habit
     */
    public void setDate(Date date){
        habit.setStartDate(date);
    }

    /**
     * @return number of times user has successfully completed this event
     */
    public int getCompleted(){
        return habit.getTimesCompleted();
    }

    /**
     * @return number of times user has failed to complete this event
     */
    public int getFailed(){
        return habit.getTimesFailed();
    }

    /**
     * @return the unique identifer for elastic search
     */
    public String getID() {
        return habit.getId();
    }

    /**
     * @return start date of current habit
     */
    public Date getStartDate(){
        return habit.getStartDate();
    }

    /**
     * @return the percentage completion for the current habit
     */
    public double getPercentageCompletion(){
        habit.updateFailed();
        return habit.getPercentCompletion();
    }

    /**
     * save current habit and add it to elastic
     */
    public void saveAddHabit(){
        habitList.addHabit(habit);
        AddHabitCommand addHabitCommand = new AddHabitCommand(habit);
        commandQueue.addTail(addHabitCommand);
    }

    /**
     * edit the current habit and save changes in elastic
     */
    public void saveEditHabit(){
        habitList.editHabit(position, habit);
        EditHabitCommand editHabitCommand = new EditHabitCommand(habit);
        commandQueue.addTail(editHabitCommand);
    }

    /**
     * @param position the position of the habit to get
     */
    public void viewHabit(int position){
        habit = habitList.getHabit(position);
        this.position = position;
    }

    /**
     * delete current habit, and all associated events, locally and from elastic
     */
    public void deleteHabit(){
        eventController.deleteAllHabitEvents(habitList.getHabit(position));
        DeleteHabitCommand deleteHabitCommand = new DeleteHabitCommand(habit);
        commandQueue.addTail(deleteHabitCommand);
        habitList.deleteHabit(position);
    }

    public void clearHabits(){
        habitList.clearAll();
    }

    /**
     * pushes all current habits to elastic
     */
    public void saveAllHabits(){
        for (int i = 0; i < habitList.getSize(); i++) {
            Habit newHabit = habitList.getHabit(i);
            AddHabitCommand addHabitCommand = new AddHabitCommand(newHabit);
            commandQueue.addTail(addHabitCommand);
        }
    }

}
