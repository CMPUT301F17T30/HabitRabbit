package com.example.cmput301f17t30.habitrabbit;

import java.util.ArrayList;

/**
 * Created by arankin on 18/10/17.
 */

public class Habit {
    public String Title;
    public String Reason;
    public ArrayList<Boolean> days;
    public Integer timesFailed;
    public Integer timesCompleted;

/**
    public Habit(String title, String reason, ArrayList<Boolean> days) {
        Title = title;
        Reason = reason;
        this.days = days;
        timesCompleted = 0;
        timesFailed =0;
    }
 **/

    public Habit(String title, ArrayList<Boolean> days) {
        Title = title;
        this.days = days;
        timesCompleted = 0;
        timesFailed = 0;
    }

    public String getTitle() {

        return Title;
    }

    public void setTitle(String title) {

        Title = title;
    }

    public String getReason() {

        return Reason;
    }

    public void setReason(String reason) {

        Reason = reason;
    }

    public ArrayList<Boolean> getDays() {

        return days;
    }

    public void setDays(ArrayList<Boolean> days) {

        this.days = days;
    }

    public Boolean isDueToday(){

        return Boolean.FALSE;
    }

    public double getPercentCompletion(){
        double percentage = (timesFailed+timesCompleted)/timesCompleted;

        return percentage;
    }

    public Integer getTimesFailed() {
        return timesFailed;
    }

    public void setTimesFailed(Integer timesFailed) {
        this.timesFailed = timesFailed;
    }

    public Integer getTimesCompleted() {
        return timesCompleted;
    }

    public void setTimesCompleted(Integer timesCompleted) {
        this.timesCompleted = timesCompleted;
    }

    public void incrementTimesFailed(){
        this.timesFailed +=1;

    }

    public void decrementTimesFailed(){
        this.timesFailed -=1;

    }

    public void incrementTimesCompleted(){
        this.timesCompleted +=1;

    }

    public void decrementTimesCompleted(){
        this.timesCompleted -=1;

    }


}
