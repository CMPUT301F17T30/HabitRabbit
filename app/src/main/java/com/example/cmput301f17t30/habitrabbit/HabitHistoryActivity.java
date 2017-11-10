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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

public class HabitHistoryActivity extends AppCompatActivity {

    private RecyclerView habitEventrecyclerView;
    private LinearLayoutManager habitEventlinearLayoutManager;
    private HabitHistoryLayoutAdapter habitEventadapter;

    private ArrayList<HabitEvent> habitHistoryList = new ArrayList<>();

    ArrayList daylist = new ArrayList<Boolean>();
    Habit habit = new Habit("title 1","test",daylist, new Date());
    HabitEvent event = new HabitEvent(habit);

    ArrayList daylist2 = new ArrayList<Boolean>();
    Habit habit1 = new Habit("title 1","test",daylist,new Date());
    HabitEvent event1 = new HabitEvent(habit);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_history);

        habitHistoryList.add(event);
        habitHistoryList.add(event1);

        habitEventrecyclerView = (RecyclerView) findViewById(R.id.recyclerViewHabitEvent);
        habitEventlinearLayoutManager = new LinearLayoutManager(this);
        habitEventrecyclerView.setLayoutManager(habitEventlinearLayoutManager);
        habitEventadapter = new HabitHistoryLayoutAdapter(habitHistoryList, this);
        habitEventrecyclerView.setAdapter(habitEventadapter);
    }
}
