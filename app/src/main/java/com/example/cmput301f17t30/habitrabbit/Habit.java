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
import java.util.Calendar;
import java.util.Date;

/**
 * Created by arankin on 18/10/17.
 */

public class Habit {
    private static final int MONDAY = 0;
    private static final int TUESDAY = 1;
    private static final int WEDNESDAY = 2;
    private static final int THURSDAY = 3;
    private static final int FRIDAY = 4;
    private static final int SATURDAY = 5;
    private static final int SUNDAY = 6;


    private String Title;
    private String Reason;
    private ArrayList<Boolean> days;
    private Integer timesFailed;
    private Integer timesCompleted;
    private Date startDate;
    private Date lastCompleted;
    private Date lastCalculated;

    /**
     *
     * @param title A unique identifier string for a type of habit
     * @param reason optional string, the reason for doing the habit
     * @param days the days of each week that the habit will be due
     * @param date the starting date of the habit
     */
    public Habit(String title, String reason, ArrayList<Boolean> days, Date date) {
        Title = title;
        Reason = reason;
        this.days = days;
        timesCompleted = 0;
        timesFailed =0;
        startDate = date;
        lastCompleted = date;
        lastCalculated = date;
    }

    /**
     *
     * @param title A unique identifier string for a type of habit
     * @param days the days of each week that the habit will be due
     * @param date the starting date of the habit
     */
    public Habit(String title, ArrayList<Boolean> days, Date date) {
        Title = title;
        this.days = days;
        timesCompleted = 0;
        timesFailed = 0;
        startDate = date;
        lastCompleted = date;
        lastCalculated = date;

    }

    /**
     *
     * @return the title of the habit
     */
    public String getTitle() {

        return Title;
    }

    /**
     *
     * @param title A unique identifier string for a type of habit
     */
    public void setTitle(String title) {

        Title = title;
    }

    /**
     *
     * @return the reason for doing the habit
     */
    public String getReason() {

        return Reason;
    }

    /**
     *
     * @param reason the reason for doing the habit
     */
    public void setReason(String reason) {

        Reason = reason;
    }

    /**
     *
     * @return the days of each week that the habit will be due
     */
    public ArrayList<Boolean> getDays() {

        return days;
    }

    /**
     *
     * @param days the days of each week that the habit will be due
     */
    public void setDays(ArrayList<Boolean> days) {

        this.days = days;
    }

    /**
     *
     * @return whether not not the habit is due to be completed today
     */
    //TODO implement this method
    public Boolean isDueToday(){

        Calendar todayCal = Calendar.getInstance();
        todayCal.setTime(new Date());

        Integer day = todayCal.get(Calendar.DAY_OF_WEEK);

        return (days.get(day));
    }

    /**
     * calculates the total percentage completion of this habit's related events
     * @return percentage of completion
     */
    public double getPercentCompletion(){

        return (double) ((timesFailed+timesCompleted)/timesCompleted);
    }

    /**
     *
     * @return number of times the user has failed to complete this habit
     */
    public Integer getTimesFailed() {
        return timesFailed;
    }

    /**
     *
     * @param timesFailed number of times the user has failed to complete this habit
     */
    public void setTimesFailed(Integer timesFailed) {
        this.timesFailed = timesFailed;
    }

    /**
     *
     * @return The number of times the user has completed this habit
     */
    public Integer getTimesCompleted() {
        return timesCompleted;
    }

    /**
     *
     * @param timesCompleted Number of times user has completed this habit
     */
    public void setTimesCompleted(Integer timesCompleted) {
        this.timesCompleted = timesCompleted;
    }

    /**
     *  Increments the times failed counter
     */
    public void incrementTimesFailed(){
        this.timesFailed +=1;

    }

    /**
     * Decrements the times failed counter
     */
    public void decrementTimesFailed(){
        this.timesFailed -=1;

    }

    /**
     * Increments the times completed counter
     */
    public void incrementTimesCompleted(){
        this.timesCompleted +=1;

    }

    /**
     * Decrements the times completed counter
     */
    public void decrementTimesCompleted(){
        this.timesCompleted -=1;

    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getLastCompleted() {
        return lastCompleted;
    }

    public void setLastCompleted(Date lastCompleted) {
        this.lastCompleted = lastCompleted;
    }

    public void addFailed(Boolean isFailed){
        if (isFailed == Boolean.TRUE){
            timesFailed += 1;
        }
    }


    //TODO needs javadoc
    public void updateFailed () {
        Calendar lastCal = Calendar.getInstance();
        lastCal.setTime(lastCalculated);

        Calendar newCal = Calendar.getInstance();
        newCal.setTime(new Date());

        if (lastCal.getTimeInMillis() == newCal.getTimeInMillis()){

        }
        else {
            while (lastCal.getTimeInMillis() < newCal.getTimeInMillis()) {
                lastCal.add(Calendar.DAY_OF_MONTH, 1);

                switch (lastCal.get(Calendar.DAY_OF_WEEK)){
                    case Calendar.MONDAY:
                        addFailed(days.get(MONDAY));
                        break;

                    case Calendar.TUESDAY:
                        addFailed(days.get(TUESDAY));
                        break;

                    case Calendar.WEDNESDAY:
                        addFailed(days.get(WEDNESDAY));
                        break;

                    case Calendar.THURSDAY:
                        addFailed(days.get(THURSDAY));
                        break;

                    case Calendar.FRIDAY:
                        addFailed(days.get(FRIDAY));
                        break;

                    case Calendar.SATURDAY:
                        addFailed(days.get(SATURDAY));
                        break;

                    case Calendar.SUNDAY:
                        addFailed(days.get(SUNDAY));
                        break;
                }

            }

            lastCalculated = newCal.getTime();

        }
    }




}
