package com.example.cmput301f17t30.habitrabbit;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;

/**
 * Created by arankin on 10/18/17.
 */

public class TestHabitEvent extends ActivityInstrumentationTestCase2 {

    public TestHabitEvent() {
        super(HabitEvent.class);
    }

    public void testGetComment(){
        Habit habit = new MockHabit();
        HabitEvent event = new HabitEvent(habit);
        assertEquals("a comment", event.getComment());
    }

    public void testSetComment(){
        Habit habit = new MockHabit();
        HabitEvent event = new HabitEvent(habit);
        event.setComment("new comment");
        assertEquals("new comment", event.getComment());
    }

    public void testGetHabitType(){
        Habit habit = new MockHabit();
        HabitEvent event = new HabitEvent(habit);
        assertEquals(habit,event.getHabitType());

    }

    public void testSetHabitType(){
        Habit habit = new MockHabit();
        HabitEvent event = new HabitEvent(habit);
        Habit habit1 = new MockHabit();
        event.setHabitType(habit1);
        assertEquals(habit1,event.getHabitType());

    }

    public void testGetLocation(){
        Habit habit = new MockHabit();
        HabitEvent event = new HabitEvent(habit);
        event.setLocation("university of alberta");
        assertEquals("university of alberta", event.getComment());
    }

    public void testGetLogitude(){
        Habit habit = new MockHabit();
        HabitEvent event = new HabitEvent(habit);
        event.setCoordinate(-100.1, 30);
        assertEquals(-100.1, event.getLogitude());
    }

    public void testGetLatitude(){
        Habit habit = new MockHabit();
        HabitEvent event = new HabitEvent(habit);
        event.setCoordinate(-100.1, 30);
        assertEquals(30, event.getLatitude());
    }




}
