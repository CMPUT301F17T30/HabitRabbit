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
 * command design pattern. this inherits from command.
 * Created by WilliamWong on 2017-11-08.
 */

public class AddHabitCommand extends Command {
    private Habit habit;
    public AddHabitCommand(Habit habit){
        this.habit = habit;
    }
    @Override
    public void execute() {
        ElasticSearchController.AddHabitTask addHabitTask = new ElasticSearchController.AddHabitTask();
        addHabitTask.execute(habit);
    }

    @Override
    public void unexecute() {

    }

    @Override
    public boolean isReversible() {
        return false;
    }
}
