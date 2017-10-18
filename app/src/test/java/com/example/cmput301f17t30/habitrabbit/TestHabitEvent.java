package com.example.cmput301f17t30.habitrabbit;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by arankin on 10/18/17.
 */

public class TestHabitEvent extends ActivityInstrumentationTestCase2 {

    public TestHabitEvent() {
        super(HabitEvent.class);
    }

    public void TestGetComment(){
        Habit habit = new Habit;
        HabitEvent event = new HabitEvent(habit, "somewhere", "a comment");
        assertEquals("a comment", event.getComment());
    }
    
    public void TestSetComment(){
        Habit habit = new Habit;
        HabitEvent event = new HabitEvent(habit, "somewhere", "a comment");
        event.setComment("new comment");
        assertEquals("new comment", event.getComment());
    }
}
