/*
 *     <HabitRabbit- A habit tracking app.>
 *     Copyright (C) <2017>
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.example.cmput301f17t30.habitrabbit;

import org.junit.Assert;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by arankin on 18/10/17.
 */

public class TestHabit extends ActivityInstrumentationTestCase2{

    public TestHabit() {
        super(Habit.class);
    }




    public void testGetTitle(){
        ArrayList daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist);
        assertEquals(habit.getTitle(), "title 1");
    }

    public void testSetTitle(){
        ArrayList daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist);
        habit.setTitle("new title");
        assertEquals(habit.getTitle(), "new title");
    }


    public void testGetReason(){
        ArrayList daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist);
        assertEquals(habit.getReason(),"test");
    }

    public void testSetReason(){
        ArrayList daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist);
        habit.setReason("new reason");
        assertEquals(habit.getReason(),"new reason");

    }
    public void testGetDays(){
        ArrayList daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist);
        assertEquals(daylist,habit.getDays());
    }

    public void testIsDueToday(){
        ArrayList daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist);
        assertEquals(Boolean.TRUE,habit.isDueToday());

    }

    public void testGetTimesFailed(){
        ArrayList daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist);
        assertEquals((Integer) 0,habit.getTimesFailed());
    }

    public void testSetTimesFailed(){
        ArrayList daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist);
        habit.setTimesFailed(5);
        assertEquals((Integer) 5,habit.getTimesFailed());
    }

    public void testGetTimesCompleted(){
        ArrayList daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist);
        assertEquals((Integer) 0,habit.getTimesCompleted());
    }

    public void testSetTimesCompleted(){
        ArrayList daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist);
        habit.setTimesCompleted(5);
        assertEquals((Integer) 5,habit.getTimesCompleted());
    }

    public void testIncrementTimesFailed(){
        ArrayList daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist);
        habit.incrementTimesFailed();
        assertEquals((Integer) 1,habit.getTimesFailed());

    }

    public void testDecrementTimesFailed(){
        ArrayList daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist);
        habit.setTimesFailed(5);
        habit.decrementTimesFailed();
        assertEquals((Integer) 4,habit.getTimesFailed());

    }

    public void testIncrementTimesCompleted(){
        ArrayList daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist);
        habit.incrementTimesCompleted();
        assertEquals((Integer) 1,habit.getTimesCompleted());

    }

    public void testDecrementTimesCompleted(){
        ArrayList daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist);
        habit.setTimesCompleted(5);
        habit.decrementTimesCompleted();
        assertEquals((Integer) 4,habit.getTimesCompleted());

    }

    public void testGetPercentCompletion(){
        ArrayList daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist);
        habit.setTimesCompleted(5);
        habit.setTimesFailed(5);
        assertEquals(0.5,habit.getPercentCompletion());

    }

}
