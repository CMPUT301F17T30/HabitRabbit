package com.example.cmput301f17t30.habitrabbit;

/**
 * Created by WilliamWong on 2017-11-06.
 *
 * This is the Example Code from the Design Pattern Slides by Ken Wong
 */

public abstract class Command {
    public abstract void execute();
    public abstract void unexecute();
    public abstract boolean isReversible();
}
