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

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by arankin on 18/10/17.
 */

public class TestHabit extends ActivityInstrumentationTestCase2{

    public TestHabit() {
        super(Habit.class);
    }


    public void testGetTitle(){
        ArrayList<Boolean> daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist,new Date());
        assertEquals(habit.getTitle(), "title 1");
    }

    public void testSetTitle(){
        ArrayList<Boolean> daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist,new Date());
        habit.setTitle("new title");
        assertEquals(habit.getTitle(), "new title");
    }


    public void testGetReason(){
        ArrayList<Boolean> daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist,new Date());
        assertEquals(habit.getReason(),"test");
    }

    public void testSetReason(){
        ArrayList<Boolean> daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist,new Date());
        habit.setReason("new reason");
        assertEquals(habit.getReason(),"new reason");

    }
    public void testGetDays(){
        ArrayList<Boolean> daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist,new Date());
        assertEquals(daylist,habit.getDays());
    }

    public void testGetTimesFailed(){
        ArrayList<Boolean> daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist,new Date());
        assertEquals((Integer) 0,habit.getTimesFailed());
    }

    public void testSetTimesFailed(){
        ArrayList<Boolean> daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist,new Date());
        habit.setTimesFailed(5);
        assertEquals((Integer) 5,habit.getTimesFailed());
    }

    public void testGetTimesCompleted(){
        ArrayList<Boolean> daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist,new Date());
        assertEquals((Integer) 0,habit.getTimesCompleted());
    }

    public void testSetTimesCompleted(){
        ArrayList<Boolean> daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist,new Date());
        habit.setTimesCompleted(5);
        assertEquals((Integer) 5,habit.getTimesCompleted());
    }

    public void testIncrementTimesFailed(){
        ArrayList<Boolean> daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist,new Date());
        habit.incrementTimesFailed();
        assertEquals((Integer) 1,habit.getTimesFailed());

    }

    public void testDecrementTimesFailed(){
        ArrayList<Boolean> daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist,new Date());
        habit.setTimesFailed(5);
        habit.decrementTimesFailed();
        assertEquals((Integer) 4,habit.getTimesFailed());

    }

    public void testIncrementTimesCompleted(){
        ArrayList<Boolean> daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist,new Date());
        habit.incrementTimesCompleted();
        assertEquals((Integer) 1,habit.getTimesCompleted());

    }

    public void testDecrementTimesCompleted(){
        ArrayList<Boolean> daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist,new Date());
        habit.setTimesCompleted(5);
        habit.decrementTimesCompleted();
        assertEquals((Integer) 4,habit.getTimesCompleted());

    }

    public void testGetPercentCompletion(){
        ArrayList<Boolean> daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist,new Date());
        habit.setTimesCompleted(5);
        habit.setTimesFailed(5);
        assertEquals(0.5,habit.getPercentCompletion());

    }

    public void testIsDueTodayTrue(){
        ArrayList<Boolean> daylist = new ArrayList<>();
        daylist.set(0,Boolean.TRUE);
        daylist.set(1,Boolean.TRUE);
        daylist.set(2,Boolean.TRUE);
        daylist.set(3,Boolean.TRUE);
        daylist.set(4,Boolean.TRUE);
        daylist.set(5,Boolean.TRUE);
        daylist.set(6,Boolean.TRUE);
        Habit habit = new Habit("title 1","test",daylist,new Date());
        assertTrue(habit.isDueToday());

    }

    public void testIsDueTodayFalse(){
        ArrayList<Boolean> daylist = new ArrayList<>();
        daylist.set(0,Boolean.FALSE);
        daylist.set(1,Boolean.FALSE);
        daylist.set(2,Boolean.FALSE);
        daylist.set(3,Boolean.FALSE);
        daylist.set(4,Boolean.FALSE);
        daylist.set(5,Boolean.FALSE);
        daylist.set(6,Boolean.FALSE);
        Habit habit = new Habit("title 1","test",daylist,new Date());
        assertFalse(habit.isDueToday());

    }

    public void testIsDueTodaySunday(){
        ArrayList<Boolean> daylist = new ArrayList<>();
        daylist.set(0,Boolean.FALSE);
        daylist.set(1,Boolean.FALSE);
        daylist.set(2,Boolean.FALSE);
        daylist.set(3,Boolean.FALSE);
        daylist.set(4,Boolean.FALSE);
        daylist.set(5,Boolean.FALSE);
        daylist.set(6,Boolean.TRUE);

        Calendar myCalendar = new GregorianCalendar(2017, 10, 12);
        Date testDate = myCalendar.getTime();
        Habit habit = new Habit("title 1","test",daylist,testDate);
        assertTrue(habit.isDueToday());
    }

    public void testIsDueTodayMonday(){
        ArrayList<Boolean> daylist = new ArrayList<>();
        daylist.set(0,Boolean.TRUE);
        daylist.set(1,Boolean.FALSE);
        daylist.set(2,Boolean.FALSE);
        daylist.set(3,Boolean.FALSE);
        daylist.set(4,Boolean.FALSE);
        daylist.set(5,Boolean.FALSE);
        daylist.set(6,Boolean.FALSE);

        Calendar myCalendar = new GregorianCalendar(2017, 10, 13);
        Date testDate = myCalendar.getTime();
        Habit habit = new Habit("title 1","test",daylist,testDate);
        assertTrue(habit.isDueToday());
    }

    public void testUpdateFailedOneWeekOneDayPerWeek(){
        ArrayList<Boolean> daylist = new ArrayList<>();
        daylist.set(0,Boolean.FALSE);
        daylist.set(1,Boolean.FALSE);
        daylist.set(2,Boolean.TRUE); //wednesday
        daylist.set(3,Boolean.FALSE);
        daylist.set(4,Boolean.FALSE);
        daylist.set(5,Boolean.FALSE);
        daylist.set(6,Boolean.FALSE);

        Habit habit = new Habit("title 1","test",daylist,new Date());

        long DAY_IN_MS = 1000 * 60 * 60 * 24;

        habit.setLastCompleted(new Date(System.currentTimeMillis()-7 * DAY_IN_MS));
        habit.setTimesFailed(0);
        habit.updateFailed();

        assertEquals(habit.getTimesFailed(),(Integer)1);

    }

    public void testUpdateFailedOneWeekTwoDaysPerWeek(){
        ArrayList<Boolean> daylist = new ArrayList<>();
        daylist.set(0,Boolean.FALSE);
        daylist.set(1,Boolean.FALSE);
        daylist.set(2,Boolean.TRUE); //wednesday
        daylist.set(3,Boolean.FALSE);
        daylist.set(4,Boolean.TRUE); // Friday
        daylist.set(5,Boolean.FALSE);
        daylist.set(6,Boolean.FALSE);

        Habit habit = new Habit("title 1","test",daylist,new Date());

        long DAY_IN_MS = 1000 * 60 * 60 * 24;

        habit.setLastCompleted(new Date(System.currentTimeMillis()-7 * DAY_IN_MS));
        habit.setTimesFailed(0);
        habit.updateFailed();

        assertEquals(habit.getTimesFailed(),(Integer)2);

    }

    public void testUpdateFailedOneWeekSevenDaysPerWeek(){
        ArrayList<Boolean> daylist = new ArrayList<>();
        daylist.set(0,Boolean.TRUE);
        daylist.set(1,Boolean.TRUE);
        daylist.set(2,Boolean.TRUE);
        daylist.set(3,Boolean.TRUE);
        daylist.set(4,Boolean.TRUE);
        daylist.set(5,Boolean.TRUE);
        daylist.set(6,Boolean.TRUE);

        Habit habit = new Habit("title 1","test",daylist,new Date());

        long DAY_IN_MS = 1000 * 60 * 60 * 24;

        habit.setLastCompleted(new Date(System.currentTimeMillis()-7 * DAY_IN_MS));
        habit.setTimesFailed(0);
        habit.updateFailed();

        assertEquals(habit.getTimesFailed(),(Integer)7);

    }

    public void testUpdateFailedOneWeekZeroDaysPerWeek(){
        ArrayList<Boolean> daylist = new ArrayList<>();
        daylist.set(0,Boolean.FALSE);
        daylist.set(1,Boolean.FALSE);
        daylist.set(2,Boolean.FALSE);
        daylist.set(3,Boolean.FALSE);
        daylist.set(4,Boolean.FALSE);
        daylist.set(5,Boolean.FALSE);
        daylist.set(6,Boolean.FALSE);

        Habit habit = new Habit("title 1","test",daylist,new Date());

        long DAY_IN_MS = 1000 * 60 * 60 * 24;

        habit.setLastCompleted(new Date(System.currentTimeMillis()-7 * DAY_IN_MS));
        habit.setTimesFailed(0);
        habit.updateFailed();

        assertEquals(habit.getTimesFailed(),(Integer)0);

    }

    public void testUpdateFailedSameDay(){
        ArrayList<Boolean> daylist = new ArrayList<>();
        daylist.set(0,Boolean.FALSE);
        daylist.set(1,Boolean.FALSE);
        daylist.set(2,Boolean.FALSE);
        daylist.set(3,Boolean.TRUE);
        daylist.set(4,Boolean.FALSE);
        daylist.set(5,Boolean.FALSE);
        daylist.set(6,Boolean.FALSE);

        Habit habit = new Habit("title 1","test",daylist,new Date());


        habit.setLastCompleted(new Date());
        habit.setTimesFailed(0);
        habit.updateFailed();

        assertEquals(habit.getTimesFailed(),(Integer)0);

    }

}
