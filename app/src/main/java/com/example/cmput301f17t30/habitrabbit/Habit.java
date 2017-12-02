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

import io.searchbox.annotations.JestId;

/**
 * A class representing a habit object, with related details.
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

    private String userID;



    @JestId
    private String id;

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
        lastCompleted = null;
        lastCalculated = null;
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
        Reason = "";
        timesCompleted = 0;
        timesFailed = 0;
        startDate = date;
        lastCompleted = null;
        lastCalculated = null;

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
     * This method is used for testing purposes.
     * @param date the date to use for 'today', default is today's actual date
     * @return whether not not the habit is due to be completed at the supplied date
     */
    public Boolean isDueToday(Date date){

        Calendar todayCal = Calendar.getInstance();
        todayCal.setTime(date);

        Integer day = todayCal.get(Calendar.DAY_OF_WEEK);

        return (daylistWrapper(day));
    }

    /**
     * default behavior is to run this method using current date
     * @return whether or not the habit is due to be completed today
     */
    public Boolean isDueToday(){
        if (startDate.after(new Date()))
            return Boolean.FALSE;
        else
            return isDueToday(new Date());
    }

    /**
     *  a wrapper function to convert from the Calendar format to our days list
     * @param i the day of the week from the Calendar
     * @return  whether or not the day has an event due
     */
    public Boolean daylistWrapper(Integer i){
        if (i == 7)
            return days.get(5);
        if (i == 6)
            return days.get(4);
        if (i == 5)
            return days.get(3);
        if (i == 4)
            return days.get(2);
        if (i == 3)
            return days.get(1);
        if (i == 2)
            return days.get(0);
        if (i == 1)
            return days.get(6);
        return Boolean.FALSE;
    }

    /**
     * calculates the total percentage completion of this habit's related events
     * @return percentage of completion
     */
    public double getPercentCompletion(){

        if ((timesCompleted+timesFailed) == 0)
            return -1;
        
        else
            return ((double)timesCompleted/(timesFailed+timesCompleted));
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
        if (this.timesFailed != 0)
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
        if (this.timesCompleted != 0)
            this.timesCompleted -=1;

    }

    /**
     * @return the start date of this habit
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the start date of this habit
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the last time this habit was successfully completed
     */
    public Date getLastCompleted() {
        return lastCompleted;
    }

    /**
     * @param lastCompleted the last time this habit was successfully completed
     */
    public void setLastCompleted(Date lastCompleted) {
        this.lastCompleted = lastCompleted;
    }

    /**
     * Increments the isFailed counter by 1
     * @param isFailed whether or not the habit has failed
     */
    public void addFailed(Boolean isFailed){
        if (isFailed == Boolean.TRUE){
            incrementTimesFailed();
        }
    }


    /**
     * updates the number of failed habit events on this habit. Iterates through the time between
     * today and the last time that this was checked.
     */
    public void updateFailed() {
        Calendar lastCal = Calendar.getInstance();

        if (lastCompleted == null && lastCalculated == null && startDate.before(new Date())){
            lastCal.setTime(startDate);
        }
        else if (lastCompleted == null && lastCalculated == null && startDate.after(new Date())){
            lastCalculated = new Date();
            lastCal.setTime(lastCalculated);
        }
        else if (lastCompleted == null)
            lastCal.setTime(lastCalculated);
        else if (lastCalculated == null) {
            lastCalculated = new Date();
            lastCal.setTime(lastCompleted);
        }
        else if (lastCompleted.after(lastCalculated))
            lastCal.setTime(lastCompleted);
        else if (lastCalculated.after(lastCompleted))
            lastCal.setTime(lastCalculated);

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        Calendar newCal = Calendar.getInstance();
        newCal.setTime(new Date());

        if (format.format(lastCal.getTime()).matches(format.format(newCal.getTime()))){

        }
        else {
            while (!format.format(lastCal.getTime()).matches(format.format(newCal.getTime()))) {
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

        }

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }




}
