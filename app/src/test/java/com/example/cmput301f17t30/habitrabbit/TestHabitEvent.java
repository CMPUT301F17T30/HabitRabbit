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
import java.util.Date;

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


    public void testGetDate() {
        Habit habit = new MockHabit();
        HabitEvent event = new HabitEvent(habit);
        Date date = new Date();
        event.setDate(date);
        assertEquals(date,event.getDate());
    }

    public void testSetDate() {
        Habit habit = new MockHabit();
        HabitEvent event = new HabitEvent(habit);
        Date date = new Date();
        event.setDate(date);
        assertEquals(date,event.getDate());
    }



}
