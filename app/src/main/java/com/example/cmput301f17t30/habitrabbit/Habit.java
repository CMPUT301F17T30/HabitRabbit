package com.example.cmput301f17t30.habitrabbit;

import java.util.ArrayList;

/**
 * Created by arankin on 18/10/17.
 */

public class Habit {
    public String Title;
    public String Reason;
    public ArrayList<Boolean> days;


    public Habit(String title, String reason, ArrayList<Boolean> days) {
        Title = title;
        Reason = reason;
        this.days = days;
    }

    public Habit(String title, ArrayList<Boolean> days) {
        Title = title;
        this.days = days;
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


}
