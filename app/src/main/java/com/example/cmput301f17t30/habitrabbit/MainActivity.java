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

public class MainActivity extends AppCompatActivity {
    
    // create a global eventlist and eventcontroller
    public static final HabitEventList eventList = new HabitEventList();
    public static final HabitEventController eventController = new HabitEventController();

    //public static final HabitList habitList = new HabitList();
    //public static final HabitController habitController = new HabitController();

    public static final String ADD_HABIT_NAME = "AddHabitName";
    public static final String ADD_HABIT_REASON = "AddHabitReason";
    public static final String ADD_HABIT_DAYS = "AddHabitDays";
    public static final String ADD_HABIT_DATE = "AddHabitDate";


    public static final String EDIT_HABIT_NAME = "EditHabitName";
    public static final String  EDIT_HABIT_REASON = "EditHabitReason";
    public static final String EDIT_HABIT_DAYS = "EditHabitDays";
    public static final String EDIT_HABIT_POSITION = "EditHabitPosition";
    public static final String EDIT_HABIT_DATE = "EditHabitDate";





    private int ADD_HABIT_REQUEST = 0;
    private int HABIT_HISTORY_REQUEST = 1;
    private int EDIT_HABIT_REQUEST = 2;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private HabitLayoutAdapter adapter;
    ArrayList daylist = new ArrayList<Boolean>();
  //  Habit habit = new Habit("title 1","test",daylist);
    // Habit habit2 = new Habit("title 2","test2",daylist);

    private ArrayList<Habit> habitList = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // habitList.add(habit);
       // habitList.add(habit2);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new HabitLayoutAdapter(habitList, this);
        recyclerView.setAdapter(adapter);

        Button addHabitButton = (Button) findViewById(R.id.addHabitButton);
        addHabitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newHabit = new Intent(MainActivity.this, AddHabitActivity.class);
                startActivityForResult(newHabit, ADD_HABIT_REQUEST);
            }
        });

        Button habitHistoryButton = (Button) findViewById(R.id.habitHistoryButton);
        habitHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent counterPage = new Intent(MainActivity.this, HabitHistoryActivity.class);
                startActivityForResult(counterPage, HABIT_HISTORY_REQUEST);
            }
        });

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "congratulations, you clicked on button 3",
                        Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

     //   habitList.add(habit);
     //   habitList.add(habit2);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new HabitLayoutAdapter(habitList, this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == ADD_HABIT_REQUEST){
            if (resultCode == RESULT_OK){

                String name = data.getStringExtra(ADD_HABIT_NAME);
                String reason = data.getStringExtra(ADD_HABIT_REASON);
                ArrayList<Boolean> days = (ArrayList<Boolean>) data.getSerializableExtra(ADD_HABIT_DAYS);
                Date date = (Date) data.getSerializableExtra(ADD_HABIT_DATE);

                Habit newHabit = new Habit(name, days, date);

                if (!reason.isEmpty()){
                    newHabit.setReason(reason);
                }

                habitList.add(newHabit);
                adapter.notifyDataSetChanged();



            }
        }

        if (requestCode == EDIT_HABIT_REQUEST){
            if (resultCode == RESULT_OK){

                String name = data.getStringExtra(EDIT_HABIT_NAME);
                String reason = data.getStringExtra(EDIT_HABIT_REASON);
                ArrayList<Boolean> days = (ArrayList<Boolean>) data.getSerializableExtra(EDIT_HABIT_DAYS);
                int position = data.getIntExtra(EDIT_HABIT_POSITION, 0);
                Date date = (Date) data.getSerializableExtra(EDIT_HABIT_DATE);


                habitList.get(position).setTitle(name);
                habitList.get(position).setReason(reason);
                habitList.get(position).setDays(days);
                habitList.get(position).setStartDate(date);

                adapter.notifyDataSetChanged();


            }
        }

    }
}
