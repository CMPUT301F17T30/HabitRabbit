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

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import static com.example.cmput301f17t30.habitrabbit.MainActivity.eventList;

public class HabitHistoryActivity extends AppCompatActivity {

    private RecyclerView habitEventrecyclerView;
    private LinearLayoutManager habitEventlinearLayoutManager;
    private HabitHistoryLayoutAdapter habitEventadapter;

    private ArrayList<HabitEvent> habitHistoryList = new ArrayList<>();
    public static final HabitEventController habitEventController = new HabitEventController();

    private int ADD_HABIT_EVENT_REQUEST = 0;
    private int EDIT_HABIT_EVENT_REQUEST = 1;

    ArrayList<Boolean> daylist = new ArrayList<Boolean>();
    Habit habit = new Habit("title 1","test",daylist, new Date());
    HabitEvent event = new HabitEvent(habit);

    ArrayList<Boolean> daylist2 = new ArrayList<Boolean>();
    Habit habit1 = new Habit("title 1","test",daylist,new Date());
    HabitEvent event1 = new HabitEvent(habit);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_history);

        eventList.addEvent(event);
        eventList.addEvent(event1);

        habitEventrecyclerView = (RecyclerView) findViewById(R.id.recyclerViewHabitEvent);
        habitEventlinearLayoutManager = new LinearLayoutManager(this);
        habitEventrecyclerView.setLayoutManager(habitEventlinearLayoutManager);
        habitEventadapter = new HabitHistoryLayoutAdapter(eventList.getList(), this);
        habitEventrecyclerView.setAdapter(habitEventadapter);


        Button addEventButton = (Button) findViewById(R.id.addEventButton);
        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newEvent = new Intent(HabitHistoryActivity.this, AddEventActivity.class);
                startActivityForResult(newEvent, ADD_HABIT_EVENT_REQUEST);


            }
        });

        Button button2 = (Button) findViewById(R.id.button8);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "congratulations, you clicked on button 2",
                        Toast.LENGTH_SHORT).show();
            }
        });

        Button button3 = (Button) findViewById(R.id.button9);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "congratulations, you clicked on button 3",
                        Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == ADD_HABIT_EVENT_REQUEST){
            if (resultCode == RESULT_OK){
                habitEventController.saveAddEvent();
                habitEventadapter.notifyDataSetChanged();
            }
        }

        if (requestCode == EDIT_HABIT_EVENT_REQUEST){
            if (resultCode == RESULT_OK){
                habitEventController.saveEditEvent();
                habitEventadapter.notifyDataSetChanged();

            }
        }

    }
}
