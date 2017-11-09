package com.example.cmput301f17t30.habitrabbit;

/**
 * Created by WilliamWong on 2017-11-08.
 */

public class DeleteHabitCommand extends Command {
    @Override
    public void execute() {
        //Using the Jest library
        /*
        client.execute(new Delete.Builder("ID from Habit in elasticsearch")
                        .index("Our Index")
                        .type("Habit")
                        .build())
         */
    }

    @Override
    public void unexecute() {

    }

    @Override
    public boolean isReversible() {
        return false;
    }
}
