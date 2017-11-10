package com.example.cmput301f17t30.habitrabbit;

import java.util.ArrayList;

/**
 * Created by arankin on 18/10/17.
 */

public class Habit {
    private String Title;
    private String Reason;
    private ArrayList<Boolean> days;
    private Integer timesFailed;
    private Integer timesCompleted;

    /**
     *
     * @param title A unique identifier string for a type of habit
     * @param reason optional string, the reason for doing the habit
     * @param days the days of each week that the habit will be due
     */
    public Habit(String title, String reason, ArrayList<Boolean> days) {
        Title = title;
        Reason = reason;
        this.days = days;
        timesCompleted = 0;
        timesFailed =0;
    }

    /**
     *
     * @param title A unique identifier string for a type of habit
     * @param days the days of each week that the habit will be due
     */
    public Habit(String title, ArrayList<Boolean> days) {
        Title = title;
        this.days = days;
        timesCompleted = 0;
        timesFailed = 0;
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

        return Boolean.FALSE;
    }

    /**
     *
     * @return percentage of possible due dates that the habit was completed
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


}
