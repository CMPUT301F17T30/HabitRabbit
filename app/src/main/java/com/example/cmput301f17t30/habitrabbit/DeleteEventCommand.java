package com.example.cmput301f17t30.habitrabbit;

/**
 * Created by WilliamWong on 2017-11-08.
 */

public class DeleteEventCommand extends Command {
    @Override
    public void execute() {
        //Using the Jest library
        /*
        client.execute(new Delete.Builder("ID of Event from elasticsearch")
                        .index("Our Index")
                        .type("Event")
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
