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

    public HabitController(){

    }

    public void addHabit(String title, ArrayList<Boolean> days, Date startDate){
        habit = new Habit(title, days, startDate);
    }

    public void editHabit(int position){
        habit = habitList.get(position);
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

    public String getTitle(int position){
        habit = habitList.get(position);
        return habit.getTitle();
    }

    public String getReason(int position){
        habit = habitList.get(position);
        return habit.getReason();
    }

    public ArrayList<Boolean> getDays(int position){
        habit = habitList.get(position);
        return habit.getDays();
    }

    public Date getStartDate(int position){
        habit = habitList.get(position);
        return habit.getStartDate();
    }

    public double getPercentageCompletion(int position){
        habit = habitList.get(position);
        habit.updateFailed();
        return habit.getPercentCompletion();
    }

    /**
     * @// TODO: 2017-11-11 make grade images
     * @param position
     * @return
     */
    public Image getGrade(int position){

    }

}
