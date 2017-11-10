package com.example.cmput301f17t30.habitrabbit;

/**
 * Created by WilliamWong on 2017-11-08.
 */

public class AddUserCommand extends Command {
    @Override
    public void execute() {
        //POST Method
        //Index: Our Index
        //Type: User
        /*
        * {
        *   "user-id" : "This is the User ID"
        * }
        * */
        //Find a way to store User ID from elasticsearch
    }

    @Override
    public void unexecute() {

    }

    @Override
    public boolean isReversible() {
        return false;
    }
}
