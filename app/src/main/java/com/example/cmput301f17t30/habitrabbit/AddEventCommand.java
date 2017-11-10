package com.example.cmput301f17t30.habitrabbit;

/**
 * Created by WilliamWong on 2017-11-08.
 */

public class AddEventCommand extends Command {
    @Override
    public void execute() {
        //POST Method
        //Index: Our Index
        //Type: Event
        /*
        * {
        *   "user-id" : "This is the User ID"
        *   "comment" : "This is the comment"
        *   "habittype" : "This is the Habit I'm part of"
        *   "date" : "This is the date"
        *   "image" : "This is the image"
        *   "location" : "This is the location"
        *   "longitude" : "This is the longitude"
        *   "latitude" : "This is the latitude"
        * }
        * */
        //Find a way to store Event ID from elasticsearch
    }

    @Override
    public void unexecute() {

    }

    @Override
    public boolean isReversible() {
        return false;
    }
}
