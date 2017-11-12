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

/**
 * Created by Adam on 11-Nov-17.
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestHabit {


    public TestHabit() {
    }

    @Test
    public void testGetTitle(){
        ArrayList<Boolean> daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist,new Date());
        assertEquals(habit.getTitle(), "title 1");
    }
    @Test
    public void testSetTitle(){
        ArrayList<Boolean> daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist,new Date());
        habit.setTitle("new title");
        assertEquals(habit.getTitle(), "new title");
    }

    @Test
    public void testGetReason(){
        ArrayList<Boolean> daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist,new Date());
        assertEquals(habit.getReason(),"test");
    }

    @Test
    public void testSetReason(){
        ArrayList<Boolean> daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist,new Date());
        habit.setReason("new reason");
        assertEquals(habit.getReason(),"new reason");

    }

    @Test
    public void testGetDays(){
        ArrayList<Boolean> daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist,new Date());
        assertEquals(daylist,habit.getDays());
    }

    @Test
    public void testGetTimesFailed(){
        ArrayList<Boolean> daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist,new Date());
        assertEquals((Integer) 0,habit.getTimesFailed());
    }

    @Test
    public void testSetTimesFailed(){
        ArrayList<Boolean> daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist,new Date());
        habit.setTimesFailed(5);
        assertEquals((Integer) 5,habit.getTimesFailed());
    }

    @Test
    public void testGetTimesCompleted(){
        ArrayList<Boolean> daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist,new Date());
        assertEquals((Integer) 0,habit.getTimesCompleted());
    }

    @Test
    public void testSetTimesCompleted(){
        ArrayList<Boolean> daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist,new Date());
        habit.setTimesCompleted(5);
        assertEquals((Integer) 5,habit.getTimesCompleted());
    }

    @Test
    public void testIncrementTimesFailed(){
        ArrayList<Boolean> daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist,new Date());
        habit.incrementTimesFailed();
        assertEquals((Integer) 1,habit.getTimesFailed());
    }

    @Test
    public void testDecrementTimesFailed(){
        ArrayList<Boolean> daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist,new Date());
        habit.setTimesFailed(5);
        habit.decrementTimesFailed();
        assertEquals((Integer) 4,habit.getTimesFailed());
    }

    @Test
    public void testDecrementTimesFailedZeroBehavior(){
        ArrayList<Boolean> daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist,new Date());
        habit.setTimesFailed(0);
        habit.decrementTimesFailed();
        assertEquals((Integer) 0,habit.getTimesFailed());
    }


    @Test
    public void testIncrementTimesCompleted(){
        ArrayList<Boolean> daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist,new Date());
        habit.incrementTimesCompleted();
        assertEquals((Integer) 1,habit.getTimesCompleted());

    }
    @Test
    public void testDecrementTimesCompleted(){
        ArrayList<Boolean> daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist,new Date());
        habit.setTimesCompleted(5);
        habit.decrementTimesCompleted();
        assertEquals((Integer) 4,habit.getTimesCompleted());
    }

    @Test
    public void testDecrementTimesCompletedZeroBehavior() {
        ArrayList<Boolean> daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1", "test", daylist, new Date());
        habit.setTimesCompleted(0);
        habit.decrementTimesCompleted();
        assertEquals((Integer) 0, habit.getTimesCompleted());
    }

    @Test
    public void testGetPercentCompletion(){
        ArrayList<Boolean> daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist,new Date());
        habit.setTimesCompleted(5);
        habit.setTimesFailed(5);
        assertEquals(0.5,habit.getPercentCompletion(),0.02);
    }

    @Test
    public void testGetPercentCompletionZeroPercent(){
        ArrayList<Boolean> daylist = new ArrayList<Boolean>();
        Habit habit = new Habit("title 1","test",daylist,new Date());
        habit.setTimesCompleted(0);
        habit.setTimesFailed(5);
        assertEquals(0,habit.getPercentCompletion(),0.02);
    }

    @Test
    public void testGetStartDate(){
        ArrayList<Boolean> daylist = new ArrayList<Boolean>();
        Date testDate = new Date();
        Habit habit = new Habit("title 1","test",daylist,testDate);
        assertEquals(testDate,habit.getStartDate());
    }

    @Test
    public void testSetStartDate(){
        ArrayList<Boolean> daylist = new ArrayList<Boolean>();
        Date testDate = new Date();
        Habit habit = new Habit("title 1","test",daylist,testDate);
        Date newDate = new Date();
        habit.setStartDate(newDate);
        assertEquals(newDate,habit.getStartDate());
    }


    @Test
    public void testIsDueTodayTrue(){
        ArrayList<Boolean> daylist = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            daylist.add(Boolean.FALSE);
        }
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
    @Test
    public void testIsDueTodayFalse(){
        ArrayList<Boolean> daylist = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            daylist.add(Boolean.FALSE);
        }
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
    @Test
    public void testIsDueTodaySunday(){
        ArrayList<Boolean> daylist = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            daylist.add(Boolean.FALSE);
        }
        daylist.set(0,Boolean.FALSE);
        daylist.set(1,Boolean.FALSE);
        daylist.set(2,Boolean.FALSE);
        daylist.set(3,Boolean.FALSE);
        daylist.set(4,Boolean.FALSE);
        daylist.set(5,Boolean.FALSE);
        daylist.set(6,Boolean.TRUE);

        GregorianCalendar myCalendar = new GregorianCalendar(2017, 10, 12);
        Date testDate = myCalendar.getTime();
        Habit habit = new Habit("title 1","test",daylist,testDate);

        long DAY_IN_MS = 1000 * 60 * 60 * 24;
        assertTrue(habit.isDueToday(testDate));
    }

    @Test
    public void testIsDueTodaySaturday(){
        ArrayList<Boolean> daylist = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            daylist.add(Boolean.FALSE);
        }
        daylist.set(0,Boolean.FALSE);
        daylist.set(1,Boolean.FALSE);
        daylist.set(2,Boolean.FALSE);
        daylist.set(3,Boolean.FALSE);
        daylist.set(4,Boolean.FALSE);
        daylist.set(5,Boolean.TRUE);
        daylist.set(6,Boolean.FALSE);

        Calendar todayCal = Calendar.getInstance();
        todayCal.setTime(new Date());

        Integer day = todayCal.get(Calendar.DAY_OF_WEEK);

        Boolean bol = (daylist.get(day-2));

        GregorianCalendar myCalendar = new GregorianCalendar(2017, 10, 11);
        Date testDate = myCalendar.getTime();
        Habit habit = new Habit("title 1","test",daylist,testDate);
        Boolean bool = habit.isDueToday();
        assertTrue(habit.isDueToday(testDate));
    }

    @Test
    public void testIsDueTodayFriday() {
        ArrayList<Boolean> daylist = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            daylist.add(Boolean.FALSE);
        }
        daylist.set(0, Boolean.FALSE);
        daylist.set(1, Boolean.FALSE);
        daylist.set(2, Boolean.FALSE);
        daylist.set(3, Boolean.FALSE);
        daylist.set(4, Boolean.TRUE);
        daylist.set(5, Boolean.FALSE);
        daylist.set(6, Boolean.FALSE);

        Calendar myCalendar = new GregorianCalendar(2017, 10, 10);
        Date testDate = myCalendar.getTime();
        Habit habit = new Habit("title 1", "test", daylist, testDate);
        assertTrue(habit.isDueToday(testDate));
    }

    @Test
    public void testIsDueTodayThursday(){
        ArrayList<Boolean> daylist = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            daylist.add(Boolean.FALSE);
        }
        daylist.set(0,Boolean.TRUE);
        daylist.set(1,Boolean.TRUE);
        daylist.set(2,Boolean.FALSE);
        daylist.set(3,Boolean.TRUE);
        daylist.set(4,Boolean.FALSE);
        daylist.set(5,Boolean.FALSE);
        daylist.set(6,Boolean.FALSE);

        Calendar myCalendar = new GregorianCalendar(2017, 10, 9);
        Date testDate = myCalendar.getTime();
        Habit habit = new Habit("title 1","test",daylist,testDate);
        assertTrue(habit.isDueToday(testDate));
    }

    @Test
    public void testIsDueTodayWednesday(){
        ArrayList<Boolean> daylist = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            daylist.add(Boolean.FALSE);
        }
        daylist.set(0,Boolean.TRUE);
        daylist.set(1,Boolean.TRUE);
        daylist.set(2,Boolean.TRUE);
        daylist.set(3,Boolean.FALSE);
        daylist.set(4,Boolean.FALSE);
        daylist.set(5,Boolean.FALSE);
        daylist.set(6,Boolean.FALSE);

        Calendar myCalendar = new GregorianCalendar(2017, 10, 8);
        Date testDate = myCalendar.getTime();
        Habit habit = new Habit("title 1","test",daylist,testDate);
        assertTrue(habit.isDueToday(testDate));
    }


    @Test
    public void testIsDueTodayMonday(){
        ArrayList<Boolean> daylist = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            daylist.add(Boolean.FALSE);
        }
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
        assertTrue(habit.isDueToday(testDate));
    }

    @Test
    public void testUpdateFailedOneWeekOneDayPerWeek(){
        ArrayList<Boolean> daylist = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            daylist.add(Boolean.FALSE);
        }
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

        Integer expected = 1;

        assertEquals(expected,habit.getTimesFailed());

    }
    @Test
    public void testUpdateFailedOneWeekTwoDaysPerWeek(){
        ArrayList<Boolean> daylist = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            daylist.add(Boolean.FALSE);
        }
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

        assertEquals((Integer)2,habit.getTimesFailed());

    }
    @Test
    public void testUpdateFailedOneWeekSevenDaysPerWeek(){
        ArrayList<Boolean> daylist = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            daylist.add(Boolean.FALSE);
        }
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

        assertEquals((Integer)7,habit.getTimesFailed());

    }
    @Test
    public void testUpdateFailedOneWeekZeroDaysPerWeek(){
        ArrayList<Boolean> daylist = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            daylist.add(Boolean.FALSE);
        }
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

        assertEquals((Integer)0,habit.getTimesFailed());

    }
    @Test
    public void testUpdateFailedSameDay(){
        ArrayList<Boolean> daylist = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            daylist.add(Boolean.FALSE);
        }
        daylist.set(0,Boolean.TRUE);
        daylist.set(1,Boolean.TRUE);
        daylist.set(2,Boolean.TRUE);
        daylist.set(3,Boolean.TRUE);
        daylist.set(4,Boolean.TRUE);
        daylist.set(5,Boolean.TRUE);
        daylist.set(6,Boolean.TRUE);

        Habit habit = new Habit("title 1","test",daylist,new Date());


        habit.setLastCompleted(new Date());
        habit.setTimesFailed(0);
        habit.updateFailed();

        assertEquals((Integer)0,habit.getTimesFailed());

    }

    @Test
    public void testUpdateFailedTwentyWeeksSevenDaysPerWeek(){
        ArrayList<Boolean> daylist = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            daylist.add(Boolean.FALSE);
        }
        daylist.set(0,Boolean.TRUE);
        daylist.set(1,Boolean.TRUE);
        daylist.set(2,Boolean.TRUE);
        daylist.set(3,Boolean.TRUE);
        daylist.set(4,Boolean.TRUE);
        daylist.set(5,Boolean.TRUE);
        daylist.set(6,Boolean.TRUE);

        Habit habit = new Habit("title 1","test",daylist,new Date());

        long DAY_IN_MS = 1000 * 60 * 60 * 24;

        habit.setLastCompleted(new Date(System.currentTimeMillis()-(7 * 20* DAY_IN_MS)));
        habit.setTimesFailed(0);
        habit.updateFailed();

        assertEquals((Integer)(7*20),habit.getTimesFailed());

    }
    @Test
    public void testUpdateFailedTwoHundredWeeksSevenDaysPerWeek(){
        ArrayList<Boolean> daylist = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            daylist.add(Boolean.FALSE);
        }
        daylist.set(0,Boolean.TRUE);
        daylist.set(1,Boolean.TRUE);
        daylist.set(2,Boolean.TRUE);
        daylist.set(3,Boolean.TRUE);
        daylist.set(4,Boolean.TRUE);
        daylist.set(5,Boolean.TRUE);
        daylist.set(6,Boolean.TRUE);

        Habit habit = new Habit("title 1","test",daylist,new Date());

        long DAY_IN_MS = 1000 * 60 * 60 * 24;

        habit.setLastCompleted(new Date(System.currentTimeMillis()-7 * 200 * DAY_IN_MS));
        habit.setTimesFailed(0);
        habit.updateFailed();

        assertEquals((Integer)(7*200),habit.getTimesFailed());

    }

    @Test
    public void testThisShouldFail() throws Exception{

        assertEquals ("should fail",-5,"string");
    }
}