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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static com.example.cmput301f17t30.habitrabbit.MainActivity.eventList;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.habitList;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.userController;

/**
 * An activity that displays all previously completed habit events.
 *
 * does not yet sort by date.
 * sort and filter functionality to be added.
 */
public class HabitHistoryActivity extends AppCompatActivity {

    private RecyclerView habitEventrecyclerView;
    private LinearLayoutManager habitEventlinearLayoutManager;
    private HabitHistoryLayoutAdapter habitEventadapter;

    private int ADD_HABIT_EVENT_REQUEST = 0;
    private int EDIT_HABIT_EVENT_REQUEST = 1;

    public static elasticDoneBoolean filterDone;
    public static elasticDoneBoolean resetHistory;
    private ArrayList<HabitEvent> adapterList;

    private Boolean filter;

    public static Boolean fromHistory = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_history);

        filter = Boolean.FALSE;
        fromHistory = Boolean.TRUE;
        adapterList = new ArrayList<>();

        adapterList.addAll(eventList.getList());

        Log.d("eventListAdapter",""+adapterList);
        Collections.sort(adapterList,new HabitHistorySorter());

        habitEventrecyclerView = (RecyclerView) findViewById(R.id.recyclerViewHabitEvent);
        habitEventlinearLayoutManager = new LinearLayoutManager(this);
        habitEventrecyclerView.setLayoutManager(habitEventlinearLayoutManager);
        habitEventadapter = new HabitHistoryLayoutAdapter(adapterList, this);
        habitEventrecyclerView.setAdapter(habitEventadapter);

        Button returnToMain = (Button) findViewById(R.id.habitHistoryFinish);
        returnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        Button filterButton = (Button) findViewById(R.id.button_filter_history);
        filterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                HistoryFilterDialogue yourDialog = new HistoryFilterDialogue(HabitHistoryActivity.this);
                yourDialog.show();
            }
        });

        Button mapButton = (Button) findViewById(R.id.map);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent map = new Intent(HabitHistoryActivity.this, EventMapsActivity.class);
                map.putExtra("list", 1);
                startActivity(map);
            }
        });

        filterDone = new elasticDoneBoolean();
        filterDone.setListener(new elasticDoneBoolean.ChangeListener() {
            @Override
            public void onChange() {
                filter = Boolean.TRUE;
                adapterList.clear();
                adapterList.addAll(eventList.getList());
                Collections.sort(adapterList,new HabitHistorySorter());
                habitEventadapter = new HabitHistoryLayoutAdapter(adapterList,HabitHistoryActivity.this);
                habitEventrecyclerView.setAdapter(habitEventadapter);
                habitEventadapter.notifyDataSetChanged();
                filterDone.reset();
            }
        });

        resetHistory = new elasticDoneBoolean();
        resetHistory.setListener(new elasticDoneBoolean.ChangeListener() {
            @Override
            public void onChange() {
                filter = Boolean.FALSE;
                adapterList.clear();
                adapterList.addAll(eventList.getList());
                Collections.sort(adapterList,new HabitHistorySorter());
                habitEventadapter = new HabitHistoryLayoutAdapter(adapterList,HabitHistoryActivity.this);
                habitEventrecyclerView.setAdapter(habitEventadapter);
                habitEventadapter.notifyDataSetChanged();
                resetHistory.reset();
            }
        });


    }

    @Override
    protected void onStart(){
        super.onStart();

        habitEventrecyclerView = (RecyclerView) findViewById(R.id.recyclerViewHabitEvent);
        habitEventlinearLayoutManager = new LinearLayoutManager(this);
        habitEventrecyclerView.setLayoutManager(habitEventlinearLayoutManager);
        adapterList.clear();
        adapterList.addAll(eventList.getList());
        Log.d("adapterList: ", ""+adapterList);
        Collections.sort(adapterList,new HabitHistorySorter());
        habitEventadapter = new HabitHistoryLayoutAdapter(adapterList, this);
        habitEventrecyclerView.setAdapter(habitEventadapter);
        habitEventadapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume(){
        super.onResume();
        //adapterList = eventList.getList();

        habitEventrecyclerView = (RecyclerView) findViewById(R.id.recyclerViewHabitEvent);
        habitEventlinearLayoutManager = new LinearLayoutManager(this);
        habitEventrecyclerView.setLayoutManager(habitEventlinearLayoutManager);
        adapterList.clear();
        adapterList.addAll(eventList.getList());
        Log.d("adapterList: ", ""+adapterList);
        Collections.sort(adapterList,new HabitHistorySorter());
        habitEventadapter = new HabitHistoryLayoutAdapter(adapterList, this);
        habitEventrecyclerView.setAdapter(habitEventadapter);
        habitEventadapter.notifyDataSetChanged();


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //adapterList = eventList.getList();

        habitEventrecyclerView = (RecyclerView) findViewById(R.id.recyclerViewHabitEvent);
        habitEventlinearLayoutManager = new LinearLayoutManager(this);
        habitEventrecyclerView.setLayoutManager(habitEventlinearLayoutManager);
        adapterList.clear();
        adapterList.addAll(eventList.getList());
        Log.d("adapterList: ", ""+adapterList);
        Collections.sort(adapterList,new HabitHistorySorter());
        habitEventadapter = new HabitHistoryLayoutAdapter(adapterList, this);
        habitEventrecyclerView.setAdapter(habitEventadapter);
        habitEventadapter.notifyDataSetChanged();



    //    adapterList.clear();
    //    adapterList.addAll(eventList.getList());
    //    habitEventadapter.notifyDataSetChanged();
    //    habitEventrecyclerView.invalidate();


    }


    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == ADD_HABIT_EVENT_REQUEST){
            if (resultCode == RESULT_OK){
                //habitEventController.saveAddEvent();
                habitEventadapter.notifyDataSetChanged();
            }
        }
        if (requestCode == EDIT_HABIT_EVENT_REQUEST){
            if (resultCode == RESULT_OK){
                //habitEventController.saveEditEvent();
                habitEventadapter.notifyDataSetChanged();

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
            Intent logout = new Intent(HabitHistoryActivity.this, LoginActivity.class);
            SharedPreferences mySPrefs = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = mySPrefs.edit();
            editor.remove("username").apply();
            startActivity(logout);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * @param filterString the text that the user wishes to search by
     * @param filterType 0 for filter by habit type, 1 for filter by comment text, -1 to reset
     */
    public void filterHistoryList(String filterString, Integer filterType){

        ArrayList<HabitEvent> originalList;
        final ArrayList<HabitEvent> filteredList;

        //maybe try getting events explicitly by position
        originalList = eventList.getList();
        filteredList = new ArrayList<>();

        if (filterType == -1){
            habitEventadapter = new HabitHistoryLayoutAdapter(eventList.getList(), this);
            habitEventrecyclerView.setAdapter(habitEventadapter);
            habitEventadapter.notifyDataSetChanged();
        }

        else if (filterType == 0) {
            /*
            Toast.makeText(this, "Searched for " + filterString + " by " + filterType.toString(), Toast.LENGTH_SHORT).show();
            if (filterString.length() == 0) {
                filteredList.addAll(originalList);
            } else {
                for (HabitEvent event : originalList) {
                    if (event.getHabitType().getTitle().toLowerCase().equals(filterString)) {
                        filteredList.add(event);
                    }
                }
            }
            */


           /* for (int i =0;i<eventList.getSize(); i++){
                HabitEvent event = eventList.getEvent(i);
                if (event.getHabitType().getTitle().toLowerCase().equals(filterString.toLowerCase())){
                    filteredList.add(event);
                }

            }*/

            for (int i =0;i<habitList.getSize(); i++) {
                String habitID;
                Habit habit = habitList.getHabit(i);
                if (habit.getTitle().toLowerCase().equals(filterString.toLowerCase())) {
                    habitID = habit.getId();
                    ElasticSearchController.GetFilteredEventsTask getFilteredEventsTask = new ElasticSearchController.GetFilteredEventsTask();
                    getFilteredEventsTask.execute(habitID);
                    break;
                }
            }


        }

        else if (filterType == 1){
            Toast.makeText(this, "Searched for " + filterString + " by " + filterType.toString(), Toast.LENGTH_SHORT).show();
            if (filterString.length() != 0) {
                ElasticSearchController.GetSearchedEventsTask getSearchedEventsTask = new ElasticSearchController.GetSearchedEventsTask();
                getSearchedEventsTask.execute(filterString);
            }
        }
    }


    @Override
    public void onBackPressed() {
        if(filter == Boolean.FALSE){
            super.onBackPressed();
        }
        else {
            ElasticSearchController.GetEventTask getEventTask = new ElasticSearchController.GetEventTask();
            getEventTask.execute(userController.getUsername());
        }

    }




    public class HabitHistorySorter implements Comparator<HabitEvent> {
        public int compare(HabitEvent a, HabitEvent b){
            return b.getDate().compareTo(a.getDate());
        }
    }





}
