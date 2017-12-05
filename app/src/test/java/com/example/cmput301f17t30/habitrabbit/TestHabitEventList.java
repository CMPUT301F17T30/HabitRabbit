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

import com.example.cmput301f17t30.habitrabbit.MockClasses.MockHabit;
import com.example.cmput301f17t30.habitrabbit.model.Habit;
import com.example.cmput301f17t30.habitrabbit.model.HabitEvent;
import com.example.cmput301f17t30.habitrabbit.model.HabitEventList;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for HabitEventList model class
 * Created by Adam on 12-Nov-17.
 */

public class TestHabitEventList {

    public TestHabitEventList() {
        super();
    }

    @Test
    public void testAddEvent(){
        Habit type = new MockHabit();
        HabitEvent event = new HabitEvent(type);
        HabitEventList list = new HabitEventList();
        list.addEvent(event);

        assertEquals(event,list.getEvent(0));
    }

    @Test
    public void testEditEvent(){
        Habit type = new MockHabit();
        Habit typeTwo = new MockHabit();
        HabitEvent event = new HabitEvent(type);
        HabitEvent eventTwo = new HabitEvent(typeTwo);
        HabitEventList list = new HabitEventList();
        list.addEvent(event);
        list.editEvent(0,eventTwo);

        assertEquals(eventTwo,list.getEvent(0));
    }

    @Test
    public void testGetSize(){
        Habit type = new MockHabit();
        HabitEvent event = new HabitEvent(type);
        HabitEvent eventTwo = new HabitEvent(type);
        HabitEventList list = new HabitEventList();
        list.addEvent(event);
        list.addEvent(eventTwo);

        assertEquals(2, list.getSize());


    }

    @Test
    public void testDeleteEvent(){
        Habit type = new MockHabit();
        HabitEvent event = new HabitEvent(type);
        HabitEventList list = new HabitEventList();
        list.addEvent(event);
        list.deleteEvent(0);

        assertEquals(0, list.getSize());


    }
}