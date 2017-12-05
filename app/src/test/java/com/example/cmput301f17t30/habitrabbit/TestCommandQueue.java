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

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * test class for commanqueue
 */

public class TestCommandQueue {

    public TestCommandQueue(){}

    @Test
    public void testAddTail(){
        Habit habit = new MockHabit();
        Command command = new AddHabitCommand(habit);
        CommandQueue commandQueue = new CommandQueue();
        commandQueue.addTail(command);
        assertEquals(command,commandQueue.checkHead());
    }

    @Test
    public void testCheckHead(){
        Habit habit = new MockHabit();
        Command command = new AddHabitCommand(habit);
        CommandQueue commandQueue = new CommandQueue();
        commandQueue.addTail(command);
        assertEquals(command,commandQueue.checkHead());
    }

    @Test
    public void testQueueBehavior(){
        Habit habit = new MockHabit();
        Command command = new AddHabitCommand(habit);
        Command command2 = new AddHabitCommand(habit);
        Command command3 = new AddHabitCommand(habit);
        CommandQueue commandQueue = new CommandQueue();
        commandQueue.addTail(command);
        assertEquals(command,commandQueue.checkHead());
        commandQueue.addTail(command2);
        assertEquals(command,commandQueue.checkHead());
        commandQueue.addTail(command3);
        assertEquals(command,commandQueue.checkHead());
    }
}
