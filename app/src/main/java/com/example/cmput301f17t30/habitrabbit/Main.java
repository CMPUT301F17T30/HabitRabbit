package com.example.cmput301f17t30.habitrabbit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class Main extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private HabitLayoutAdapter adapter;
    ArrayList daylist = new ArrayList<Boolean>();
    Habit habit = new Habit("title 1","test",daylist);
    Habit habit2 = new Habit("title 2","test2",daylist);

    private ArrayList<Habit> habitList = new ArrayList<Habit>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        habitList.add(habit);
        habitList.add(habit2);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new HabitLayoutAdapter(habitList, this);
        recyclerView.setAdapter(adapter);

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "congratulations, you clicked on button 1",
                        Toast.LENGTH_SHORT).show();
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "congratulations, you clicked on button 2",
                        Toast.LENGTH_SHORT).show();
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

        habitList.add(habit);
        habitList.add(habit2);

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
}
