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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Sets achivements as complete, and loads achievment status
 */

public class AchievementController {
    private ArrayList<Achievement> achievementList = new ArrayList<>();
    private ArrayList<Integer> isCompletedList;
    private Achievement weekendWarriorAchievement = new Achievement(10,"Complete 10 habits on a weekend", "Weekend Warrior");
    private Achievement busyAchievement = new Achievement(3,"complete 3 habits in one day","Busy Beaver");
    private Date busyDate = new Date();
    private Achievement firstEventAchievement = new Achievement(1,"complete your first habit","Good Start");
    private Achievement openAppAchievement = new Achievement(1,"you opened the app","Too Easy");
    private Achievement newYearsAchievement = new Achievement(1,"Start a habit on New Years Eve","New Years Resolution");

    public AchievementController(){
        achievementList.add(openAppAchievement);
        achievementList.add(weekendWarriorAchievement);
        achievementList.add(busyAchievement);
        achievementList.add(newYearsAchievement);
        achievementList.add(firstEventAchievement);
    }

    public ArrayList<Achievement> getAchievements() {
        return this.achievementList;
    }

    /**
     * get the progress for each achievement
     */
    public void loadAchievementsStatus(){
        ArrayList<Achievement> list = this.achievementList;
        for (Achievement a : list){
            if (a.getProgress() >= a.getProgressRequired()){
                a.setCompleted();
            }

        }
    }

    /**
     * save the progress for each achievement
     */
    public void saveAchievementsStatus(){}

    /**
     * updates the progress for the achievement weekendwarrior
     * and sets completion if needed
     */
    public void updateWeekendWarrior(){
        Calendar calendar = new GregorianCalendar();
        // if it is a weekend, update the achievement
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            weekendWarriorAchievement.incrementProgress();
        }
        if ((weekendWarriorAchievement.getProgress() >= weekendWarriorAchievement.getProgressRequired())) {
            weekendWarriorAchievement.setCompleted();
        }

    }

    /**
     * updates the progress for the achievement busy beaver and sets
     * completion if needed
     */
    public void updateBusyBeaver(){
        Date currentDate = new Date();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        if (fmt.format(this.busyDate).equals(fmt.format(currentDate))){
            busyAchievement.incrementProgress();
        }
        else {
            busyAchievement.setProgress(1);
            this.busyDate = new Date();
        }

        if (busyAchievement.getProgress().equals(busyAchievement.getProgressRequired())){
            busyAchievement.setCompleted();
        }

    }

    /**
     * updates the progress for the achievement good start and sets
     * completion if needed
     */
    public void updateFirstEvent()
    {
        firstEventAchievement.setCompleted();
    }

    /**
     * sets completion of the achievement too easy
     */
    public void setOpenAppAchievement(){
        openAppAchievement.setCompleted();
    }

    /**
     *  sets completion for the achievement new years resolution if the condition
     *  is met
     */
    public void updateNewYearsResolution(){
        Date date1 = new Date(2011, Calendar.DECEMBER, 31);
        SimpleDateFormat fmt = new SimpleDateFormat("MMdd");
        if (fmt.format(new Date()).equals(fmt.format(date1))){
           newYearsAchievement.setCompleted();
        }
    }

}
