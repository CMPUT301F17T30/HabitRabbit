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

package com.example.cmput301f17t30.habitrabbit.MockClasses;



import com.example.cmput301f17t30.habitrabbit.model.Habit;

import java.util.ArrayList;
import java.util.Date;


/**
 * This is a mock habit object used for testing.
 */
public class MockHabit extends Habit {
    public String Title;
    public String Reason;
    public ArrayList<Boolean> days;
    public Integer timesFailed;
    public Integer timesCompleted;



    public MockHabit(){
        super("bob", new ArrayList<Boolean>(), new Date());

        Title = "Testhabit";
        Reason = "for testing";
        this.days = new ArrayList<Boolean>(){{
            add(Boolean.TRUE);
            add(Boolean.FALSE);
            add(Boolean.FALSE);
            add(Boolean.TRUE);
            add(Boolean.FALSE);
            add(Boolean.FALSE);
            add(Boolean.FALSE);
        }};
        timesCompleted = 5;
        timesFailed =5;
    }
}
