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

import android.os.AsyncTask;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.util.ArrayList;
import java.util.List;

import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;


import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.util.ArrayList;
import java.util.List;

import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

/**
 * The main activity of the app. Displays a list of habits.
 *
 * @see com.example.cmput301f17t30.habitrabbit.HabitLayoutAdapter
 */
public class MainActivity extends AppCompatActivity {

    public static final User currentUser = new User();
    public static final FriendList friendsList = new FriendList();
    public static final HabitList habitList = new HabitList();
    public static final HabitEventList eventList = new HabitEventList();

    public static final HabitEventController eventController = new HabitEventController();
    public static final HabitController habitController = new HabitController();
    public static final UserController userController = new UserController();
    public static final AchievementController achievementController = new AchievementController();
    public static final FriendController friendController = new FriendController();

    public static final CommandQueue commandQueue = new CommandQueue();

    private int ADD_HABIT_REQUEST = 0;
    private int HABIT_HISTORY_REQUEST = 1;
    public static int VIEW_HABIT_REQUEST = 2;
    private int FRIENDS_REQUEST = 3;
    private static JestDroidClient client;

    public static elasticDoneBoolean elasticDone;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private HabitLayoutAdapter adapter;
    private ArrayList<Habit> adapterList;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        commandQueue.runCommands();
        achievementController.setOpenAppAchievement();
        achievementController.loadAchievementsStatus();

        if (sharedPreferences.getString("username",null) == null){
            Intent logout = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(logout);
        }
        else{
            //TODO make this work offline
            String username = sharedPreferences.getString("username",null);
            ElasticSearchController.GetUserTask getUserTask = new ElasticSearchController.GetUserTask();
            getUserTask.execute(username);
        }

        adapterList = new ArrayList<Habit>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new HabitLayoutAdapter(adapterList, this);
        recyclerView.setAdapter(adapter);

        ElasticSearchController.GetHabitsTask getHabitsTask = new ElasticSearchController.GetHabitsTask();
        getHabitsTask.execute(userController.getUsername());



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
                Intent friendsIntent = new Intent(MainActivity.this, FriendActivity.class);
                startActivityForResult(friendsIntent, FRIENDS_REQUEST);

            }
        });

        elasticDone = new elasticDoneBoolean();
        elasticDone.setListener(new elasticDoneBoolean.ChangeListener() {
            @Override
            public void onChange() {
                adapterList.clear();
                adapterList.addAll(habitList.getList());
                adapter.notifyDataSetChanged();
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
        adapterList.clear();
        adapterList.addAll(habitList.getList());
        adapter = new HabitLayoutAdapter(adapterList, this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        invalidateOptionsMenu();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapterList.clear();
        adapterList.addAll(habitList.getList());
        adapter = new HabitLayoutAdapter(adapterList, this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();
        habitController.saveAllHabits();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapterList.clear();
        adapterList.addAll(habitList.getList());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == ADD_HABIT_REQUEST){
            if (resultCode == RESULT_OK){
                habitController.saveAddHabit();
                adapterList.clear();
                adapterList.addAll(habitList.getList());
                adapter.notifyDataSetChanged();
            }
        }
        if (requestCode == VIEW_HABIT_REQUEST){
            if (resultCode == RESULT_OK){
                habitController.saveEditHabit();
                adapterList.clear();
                adapterList.addAll(habitList.getList());
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        String username = sharedPreferences.getString("username",null);
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
            habitController.saveAllHabits();
            Intent logout = new Intent(MainActivity.this, LoginActivity.class);
            SharedPreferences mySPrefs =PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = mySPrefs.edit();
            editor.remove("username").apply();
            startActivity(logout);
        }
        if (id == R.id.user_profile_button) {
            Intent profile = new Intent(MainActivity.this, UserProfileActivity.class);
            startActivity(profile);
        }
        return super.onOptionsItemSelected(item);
    }
}




