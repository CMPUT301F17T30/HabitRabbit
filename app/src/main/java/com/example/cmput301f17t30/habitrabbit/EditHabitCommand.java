package com.example.cmput301f17t30.habitrabbit;

/**
 * Created by WilliamWong on 2017-11-08.
 */

public class EditHabitCommand extends Command {
    private Habit oldHabit;
    private Habit editedHabit;
    private User user;
    @Override
    public void execute() {
        //POST Method
        //Index: Our Index
        //Type: Habit
        //ID: ID of Habit in elasticsearch
        /*
        * {
        *   "user-id" : "This is the user id"
        *   "title" : "This is a Title"
        *   "reason" : "This is a reason"
        *   "days" : "These are the days"
        *   "failed" : "This is how many times I failed" -- Do we add this?
        *   "completed" : "This is how many times I completed" -- Do we add this?
        * }
        * */
    }

    @Override
    public void unexecute() {

    }

    @Override
    public boolean isReversible() {
        return false;
    }
}
