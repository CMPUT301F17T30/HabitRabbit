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

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Adam on 12-Nov-17.
 */

public class TestHabitList {

    public TestHabitList() {
        super();
    }

    @Test
    public void testAddHabit(){
        HabitList list = new HabitList();
        Habit habit = new MockHabit();
        list.addHabit(habit);
        assertEquals(habit,list.getHabit(0));

    }

    @Test
    public void testEditHabit(){
        HabitList list = new HabitList();
        Habit habit = new MockHabit();
        Habit newHabit = new Habit("tester",new ArrayList<Boolean>(),new Date());
        list.addHabit(habit);
        list.editHabit(0,newHabit);
        assertEquals(newHabit,list.getHabit(0));

    }

    @Test
    public void testGetSize(){
        HabitList list = new HabitList();
        Habit habit = new MockHabit();
        Habit habitTwo = new MockHabit();
        list.addHabit(habit);
        list.addHabit(habitTwo);
        assertEquals(2, list.getSize());

    }

    @Test
    public void testDeleteHabit(){
        HabitList list = new HabitList();
        Habit habit = new MockHabit();
        list.addHabit(habit);
        list.deleteHabit(0);
        assertEquals(0, list.getSize());

    }

    @Test
    public void testDeleteHabitOutOfBounds(){
        HabitList list = new HabitList();
        Habit habit = new MockHabit();
        list.addHabit(habit);
        list.deleteHabit(7);
        assertEquals(0, list.getSize());

    }




}
