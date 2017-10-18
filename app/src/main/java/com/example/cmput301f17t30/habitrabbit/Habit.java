package com.example.cmput301f17t30.habitrabbit;

import java.util.ArrayList;

/**
 * Created by arankin on 18/10/17.
 */

public class Habit {
    public String Title;
    public String Name;
    public ArrayList<Boolean> days;


    public Habit(String title, String name, ArrayList<Boolean> days) {
        Title = title;
        Name = name;
        this.days = days;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ArrayList<Boolean> getDays() {
        return days;
    }

    public void setDays(ArrayList<Boolean> days) {
        this.days = days;
    }
}
