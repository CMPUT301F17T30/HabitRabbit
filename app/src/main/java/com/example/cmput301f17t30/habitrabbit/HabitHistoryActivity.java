package com.example.cmput301f17t30.habitrabbit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class HabitHistoryActivity extends AppCompatActivity {

    private RecyclerView habitEventrecyclerView;
    private LinearLayoutManager habitEventlinearLayoutManager;
    private HabitHistoryLayoutAdapter habitEventadapter;

    private ArrayList<HabitEvent> habitHistoryList = new ArrayList<>();

    ArrayList daylist = new ArrayList<Boolean>();
    Habit habit = new Habit("title 1","test",daylist);
    HabitEvent event = new HabitEvent(habit);

    ArrayList daylist2 = new ArrayList<Boolean>();
    Habit habit1 = new Habit("title 1","test",daylist);
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
