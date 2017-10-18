package com.example.cmput301f17t30.habitrabbit;

/**
 * Created by arankin on 10/18/17.
 */

public class HabitEvent {
    public String Comment;
    public Habit habitType;
    public String Location;

    public HabitEvent(Habit habitType, String location, String comment) {
        Comment = comment;
        this.habitType = habitType;
        Location = location;
    }

    public HabitEvent(Habit habitType, String location) {
        this.habitType = habitType;
        Location = location;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public Habit getHabitType() {
        return habitType;
    }

    public void setHabitType(Habit habitType) {
        this.habitType = habitType;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
