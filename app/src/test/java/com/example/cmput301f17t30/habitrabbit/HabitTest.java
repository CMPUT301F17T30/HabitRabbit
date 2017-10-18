package com.example.cmput301f17t30.habitrabbit;

import org.junit.Assert;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by resplendent_lord_of_conquest on 18/10/17.
 */

public class HabitTest extends ActivityInstrumentationTestCase2{

    public HabitTest() {
        super(Habit.class);
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();

    }


    public void TestGetTitle(){
        ArrayList daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist);
        assertEquals(habit.getTitle(), "title 1");
    }

    public void TestSetTitle(){
        ArrayList daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist);
        habit.setTitle("new title");
        assertEquals(habit.getTitle(), "new title");
    }
}
