package com.example.cmput301f17t30.habitrabbit;

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

    public void editHabit(int position){
        habit = habitList.getHabit(position);
        this.position = position;
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

    /**
     * @// TODO: 2017-11-11 make grade images
     * @param position
     * @return
     */
   // public Image getGrade(int position){
   // }

}
