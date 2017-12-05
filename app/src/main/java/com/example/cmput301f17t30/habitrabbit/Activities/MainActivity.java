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

package com.example.cmput301f17t30.habitrabbit.Activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.example.cmput301f17t30.habitrabbit.Controllers.CommandQueue;
import com.example.cmput301f17t30.habitrabbit.Controllers.AchievementController;
import com.example.cmput301f17t30.habitrabbit.Controllers.ElasticSearchController;
import com.example.cmput301f17t30.habitrabbit.Controllers.FriendController;
import com.example.cmput301f17t30.habitrabbit.model.FriendList;
import com.example.cmput301f17t30.habitrabbit.model.FriendRequestList;
import com.example.cmput301f17t30.habitrabbit.model.Habit;
import com.example.cmput301f17t30.habitrabbit.Controllers.HabitController;
import com.example.cmput301f17t30.habitrabbit.Controllers.HabitEventController;
import com.example.cmput301f17t30.habitrabbit.model.HabitEventList;
import com.example.cmput301f17t30.habitrabbit.model.HabitList;
import com.example.cmput301f17t30.habitrabbit.R;
import com.example.cmput301f17t30.habitrabbit.model.User;
import com.example.cmput301f17t30.habitrabbit.Controllers.UserController;
import com.example.cmput301f17t30.habitrabbit.Utils.elasticDoneBoolean;

import java.util.ArrayList;

/**
 * The main activity of the app. Displays a list of habits.
 *
 * @see HabitLayoutAdapter
 */
public class MainActivity extends AppCompatActivity {

    public static final User currentUser = new User();
    public static final FriendRequestList friendRequests = new FriendRequestList();
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

    public static elasticDoneBoolean elasticDone;
    public static elasticDoneBoolean userLoad;
    public static elasticDoneBoolean friendLoad;
    public static Boolean fromMain = Boolean.FALSE;
    public static elasticDoneBoolean eventLoad;

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
            finish();
        }
        else{
            String username = sharedPreferences.getString("username",null);
            fromMain = Boolean.TRUE;
            ElasticSearchController.GetUserTask getUserTask = new ElasticSearchController.GetUserTask();
            getUserTask.execute(username);
        }


        adapterList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new HabitLayoutAdapter(adapterList, this);
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
                Intent friendsIntent = new Intent(MainActivity.this, FriendActivity.class);
                startActivityForResult(friendsIntent, FRIENDS_REQUEST);

            }
        });

        elasticDone = new elasticDoneBoolean();
        elasticDone.setListener(new elasticDoneBoolean.ChangeListener() {
            @Override
            public void onChange() {
                habitList.sort();
                adapterList.clear();
                adapterList.addAll(habitList.getList());
                adapter.notifyDataSetChanged();
            }
        });

        friendLoad = new elasticDoneBoolean();

        userLoad = new elasticDoneBoolean();
        userLoad.setListener(new elasticDoneBoolean.ChangeListener() {
            @Override
            public void onChange() {
                ElasticSearchController.GetHabitsTask getHabitsTask = new ElasticSearchController.GetHabitsTask();
                getHabitsTask.execute(userController.getUsername());

                ElasticSearchController.GetFriendTask getFriendsTask = new ElasticSearchController.GetFriendTask();
                getFriendsTask.execute("dummy");

                ElasticSearchController.GetFriendRequestTask getRequests = new ElasticSearchController.GetFriendRequestTask();
                getRequests.execute(userController.getUsername());

                invalidateOptionsMenu();

                ElasticSearchController.GetEventTask getEventTask = new ElasticSearchController.GetEventTask();
                getEventTask.execute(userController.getUsername());

            }
        });

        Log.d("eventListMain", ""+eventList.getList());

        friendLoad.setListener(new elasticDoneBoolean.ChangeListener() {
            @Override
            public void onChange() {
                ElasticSearchController.GetFriendEventsTask getEvents = new ElasticSearchController.GetFriendEventsTask();
                getEvents.execute("dummy");
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
                habitList.sort();
                adapterList.clear();
                adapterList.addAll(habitList.getList());
                adapter.notifyDataSetChanged();
            }
        }
        if (requestCode == VIEW_HABIT_REQUEST){
            if (resultCode == RESULT_OK){
                habitList.sort();
                adapterList.clear();
                adapterList.addAll(habitList.getList());
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
            fromMain = Boolean.FALSE;
            habitController.saveAllHabits();
            userController.clearUser();
            eventList.clearEventList();

            //remove user from preferences
            SharedPreferences mySPrefs =PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = mySPrefs.edit();
            editor.remove("username").apply();

            Intent mStartActivity = new Intent(this, LoginActivity.class);
            int mPendingIntentId = 123456;
            PendingIntent mPendingIntent = PendingIntent.getActivity(this, mPendingIntentId,    mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
            AlarmManager mgr = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
            System.exit(0);

        }
        if (id == R.id.user_profile_button) {
            Intent profile = new Intent(MainActivity.this, UserProfileActivity.class);
            startActivity(profile);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

    }
}




