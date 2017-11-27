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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * The main activity of the app. Displays a list of habits.
 *
 * @see com.example.cmput301f17t30.habitrabbit.HabitLayoutAdapter
 */
public class MainActivity extends AppCompatActivity {
    
    // create a global eventlist and eventcontroller
    public static final HabitEventList eventList = new HabitEventList();
    public static final HabitEventController eventController = new HabitEventController();

    public static final HabitList habitList = new HabitList();
    public static final HabitController habitController = new HabitController();
    public static final UserController userController = new UserController();

    private int ADD_HABIT_REQUEST = 0;
    private int HABIT_HISTORY_REQUEST = 1;
    public static int VIEW_HABIT_REQUEST = 3;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private HabitLayoutAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (userController.getUsername() == null){
            Intent logout = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(logout);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new HabitLayoutAdapter(habitList.getList(), this);
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
                Intent habitHistoryIntent = new Intent(MainActivity.this, HabitHistoryActivity.class);
                startActivityForResult(habitHistoryIntent, HABIT_HISTORY_REQUEST);
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

        invalidateOptionsMenu();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new HabitLayoutAdapter(habitList.getList(), this);
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
                habitController.saveAddHabit();
                adapter.notifyDataSetChanged();
            }
        }
        if (requestCode == VIEW_HABIT_REQUEST){
            if (resultCode == RESULT_OK){
                habitController.saveEditHabit();
                adapter.notifyDataSetChanged();
            }
        }

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        String username = userController.getUsername();

        menu.findItem(R.id.user_profile_button).setTitle(username);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle menu button stuff
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout_button) {
            Intent logout = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(logout);
        }

        if (id == R.id.user_profile_button) {
            Intent profile = new Intent(MainActivity.this, UserProfileActivity.class);
            startActivity(profile);
        }
        return super.onOptionsItemSelected(item);
    }

}
